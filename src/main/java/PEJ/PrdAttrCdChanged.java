package PEJ;

public class PrdAttrCdChanged extends AbstractEvent {

    private Long id;
    private String prdId;
    private String prdAttrCd;

    public PrdAttrCdChanged(){
        super();
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
    public String getPrdAttrCd() {
        return prdAttrCd;
    }

    public void setPrdAttrCd(String prdAttrCd) {
        this.prdAttrCd = prdAttrCd;
    }
}
