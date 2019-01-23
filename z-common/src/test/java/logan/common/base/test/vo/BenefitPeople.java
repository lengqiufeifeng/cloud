package logan.common.base.test.vo;

import java.util.List;

/**
 * @author zhoupan
 */
public class BenefitPeople {

    /**
     * 外修项名称
     * this is benefitPeopleName
     * Y
     */
    private String benefitPeopleName;

    /**
     * 金额
     * this is money
     * Y
     */
    private String money;

    /**
     * 推荐修理厂
     * this is repairShopName
     * Y
     */
    private String repairShopName;

    /**
     * 推荐修理厂编码
     * this is repairShopCode
     * N
     */
    private String repairShopCode;

    /**
     * 照片视频路径
     * this is ossKeys
     * N
     */
    private List<String> ossKeys;

    public void setBenefitPeopleName(String benefitPeopleName) {
        this.benefitPeopleName = benefitPeopleName;
    }

    public String getBenefitPeopleName() {
        return this.benefitPeopleName;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return this.money;
    }

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

    public void setOssKeys(List<String> ossKeys) {
        this.ossKeys = ossKeys;
    }

    public List<String> getOssKeys() {
        return this.ossKeys;
    }
}