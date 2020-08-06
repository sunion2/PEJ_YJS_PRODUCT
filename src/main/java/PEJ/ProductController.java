package PEJ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
 public class ProductController {

  @Autowired
  ProductRepository productRepository;

  @RequestMapping(method=RequestMethod.POST, path="/cancellations2")
  public void changePrdAttrCd(@RequestBody Product product) {

   System.out.println("###cancelDelivery_0" + product.getPrdId());
   System.out.println("###cancelDelivery_0" + product.getPrdAttrCd());

//   try {
//    Thread.currentThread().sleep((long) (400 + Math.random() * 220));
//   } catch (InterruptedException e) {
//    e.printStackTrace();
//   }

//   Optional<Product> productOptional = productRepository.findAllByPrdIdLike(product.getPrdId());
//   Product productAttrCd = productOptional.get();
//   productAttrCd.setPrdAttrCd("");

   Product onePlusCancelled = productRepository.findByPrdId(product.getPrdId());
   onePlusCancelled.setPrdAttrCd("CANCELLED");
   productRepository.save(onePlusCancelled);

  }
 }
