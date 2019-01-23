package logan.common.base.test.vo;


/**
 * @author zhoupan
 */
public class CaseNoImg {

    /**
     * 案件号
     * this is caseNo
     * Y
     */
    private String caseNo;

    /**
     * 任务号
     * this is lossId
     * N
     */
    private String lossId;

    /**
     * 明细项编号
     * this is detailId
     * N
     */
    private String detailId;

    /**
     * 图片类型
     * this is imgType
     */
    private String imgType;

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseNo() {
        return this.caseNo;
    }

    public void setLossId(String lossId) {
        this.lossId = lossId;
    }

    public String getLossId() {
        return this.lossId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getDetailId() {
        return this.detailId;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getImgType() {
        return this.imgType;
    }
}