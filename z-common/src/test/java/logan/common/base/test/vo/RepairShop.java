package logan.common.base.test.vo;


/**
 * @author zhoupan
 */
public class RepairShop {

    /**
     * 修理厂
     * this is repairShopName
     * Y
     */
    private String repairShopName;

    /**
     * 修理厂编码
     * this is repairShopCode
     * Y
     */
    private String repairShopCode;

    /**
     * 类型
     * this is repairShopType
     */
    private String repairShopType;

    public void setRepairShopName(String repairShopName) {
        this.repairShopName = repairShopName;
    }

    public String getRepairShopName() {
        return this.repairShopName;
    }

    public void setRepairShopCode(String repairShopCode) {
        this.repairShopCode = repairShopCode;
    }

    public String getRepairShopCode() {
        return this.repairShopCode;
    }

    public void setRepairShopType(String repairShopType) {
        this.repairShopType = repairShopType;
    }

    public String getRepairShopType() {
        return this.repairShopType;
    }
}