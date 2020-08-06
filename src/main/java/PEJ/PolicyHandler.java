package PEJ;

import PEJ.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    ProductRepository productRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchased_ChangeInventory(@Payload Purchased purchased){

        System.out.println("#####START");

        if(purchased.isMe()){
            System.out.println("#####START_1");

            Optional<Product> productOptional = productRepository.findAllByPrdIdLike(purchased.getPrdId());

            Product product = productOptional.get();

            System.out.println("#####START_2");
            System.out.println("#####product.getPrdQty()" + product.getPrdQty());
            System.out.println("#####purchased.getPurchaseQty()" + purchased.getPurchaseQty());

            // 품절여부체크
            product.setPrdQty(product.getPrdQty() - purchased.getPurchaseQty());

            System.out.println("#####product.getPrdQty" + product.getPrdQty());

            System.out.println("#####START_3");

            if( product.getPrdQty() < 0 ){
                System.out.println("productOutOfStock 이벤트 발생");
                OutOfStock outOfStock = new OutOfStock();
                outOfStock.setPrdId(purchased.getPrdId());
                outOfStock.setPurchaseId(purchased.getPurchaseId());
                outOfStock.publish();
                System.out.println("##### listener ChangeInventory : 구매 >> 실패");

            }  else if( "ONEPLUS".equals(product.getPrdAttrCd())  ){
                System.out.println("원플러스 이벤트 발생");
                OutOfStock outOfStock = new OutOfStock();
                outOfStock.setPrdId(purchased.getPrdId());
                outOfStock.setPurchaseId(purchased.getPurchaseId());
                outOfStock.setEventType("ONEPLUS");
                outOfStock.publish();
                System.out.println("##### 원플러스 이벤트 발생 : 구매 >> 지출금액 반값으로 처리");

            }

            else{
                // 품절이 아닌 경우만 재고수정
                productRepository.save(product);
                System.out.println("##### listener ChangeInventory : 구매 >> 성공");
            }

            System.out.println("##### 2222listener ChangeInventory : " + purchased.toJson());

        }


    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCancelled_ChangeInventory(@Payload Cancelled cancelled){

        if(cancelled.isMe()){

            Optional<Product> productOptional = productRepository.findAllByPrdIdLike(cancelled.getPrdId());
            Product product = productOptional.get();

            int prdQty = product.getPrdQty() + cancelled.getPurchaseQty();

            product.setPrdQty(prdQty);

            productRepository.save(product);
            System.out.println("##### listener Order : " + cancelled.toJson());

        }
        System.out.println("##### 2222listener ChangeInventory : " + cancelled.toJson());
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShipped_ReceiveProduct(@Payload Shipped shipped){

        if(shipped.isMe()){
            System.out.println("##### listener ReceiveProduct : " + shipped.toJson());
        }

        if(shipped.isMe()){
            Product product = new Product();
            product.setPrdId(shipped.getPrdId());
            product.setPrdQty(shipped.getPrdQty());
            product.setPrdNm(shipped.getPrdNm());
            product.setPrdPrice(shipped.getPrdPrice());

            productRepository.save(product);
        }

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSetOnePlused_ChangeStatus(@Payload SetOnePlused setOnePlused){

        if(setOnePlused.isMe()){
            System.out.println("##### listener ChangeStatus : " + setOnePlused.toJson());

            Optional<Product> productOptional = productRepository.findAllByPrdIdLike(setOnePlused.getPrdId());

            if( productOptional.isPresent() ) {
                Product product = productOptional.get();
                product.setPrdAttrCd(setOnePlused.getPrdAttrCd());
                productRepository.save(product);
            }

        }
    }

}
