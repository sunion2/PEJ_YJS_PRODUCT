package PEJ;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Product_table")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String prdId;
    private Integer prdQty;
    private Integer prdPrice;
    private String prdNm;
    private String prdAttrCd;

    @PostPersist
    public void onPostPersist(){
        ProductReceived productReceived = new ProductReceived();
        BeanUtils.copyProperties(this, productReceived);
        productReceived.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        InventoryChanged inventoryChanged = new InventoryChanged();
        BeanUtils.copyProperties(this, inventoryChanged);
        inventoryChanged.publishAfterCommit();


        OutOfStock outOfStock = new OutOfStock();
        BeanUtils.copyProperties(this, outOfStock);
        outOfStock.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }
    public Integer getPrdQty() {
        return prdQty;
    }

    public void setPrdQty(Integer prdQty) {
        this.prdQty = prdQty;
    }
    public Integer getPrdPrice() {
        return prdPrice;
    }

    public void setPrdPrice(Integer prdPrice) {
        this.prdPrice = prdPrice;
    }
    public String getPrdNm() {
        return prdNm;
    }

    public void setPrdNm(String prdNm) {
        this.prdNm = prdNm;
    }

    public String getPrdAttrCd() {
        return prdAttrCd;
    }

    public void setPrdAttrCd(String prdAttrCd) {
        this.prdAttrCd = prdAttrCd;
    }
}
