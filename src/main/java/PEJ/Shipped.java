package PEJ;

public class Shipped extends AbstractEvent {

    private Long id;
    private String orderId;
    private String deliveryStatus;
    private String prdId;
    private Integer prdQty;
    private String deliveryId;
    private String eventType;
    private String prdNm;
    private Integer prdPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
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
    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public String getPrdNm() {
        return prdNm;
    }

    public void setPrdNm(String prdNm) {
        this.prdNm = prdNm;
    }
    public Integer getPrdPrice() {
        return prdPrice;
    }

    public void setPrdPrice(Integer prdPrice) {
        this.prdPrice = prdPrice;
    }
}