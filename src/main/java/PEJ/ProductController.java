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

   Optional<Product> productOptional = productRepository.findAllByPrdIdLike(product.getPrdId());

   Product productAttrCd = productOptional.get();

   productAttrCd.setPrdAttrCd("");

   productRepository.save(product);

  }
 }
