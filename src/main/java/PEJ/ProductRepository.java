package PEJ;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{

        //@Query("select p.prdId,p.prdQty from Product p where p.prdId = ?1")
        //Optional<Product> findAllByPrdId(String prdId);
        Optional<Product> findAllByPrdIdLike(String prdId);
}