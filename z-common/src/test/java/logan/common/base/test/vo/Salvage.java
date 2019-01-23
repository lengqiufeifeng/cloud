package logan.common.base.test.vo;

import java.util.List;

/**
 * @author zhoupan
 */
public class Salvage {

    /**
     * 残值项名称
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

    public void setOssKeys(List<String> ossKeys) {
        this.ossKeys = ossKeys;
    }

    public List<String> getOssKeys() {
        return this.ossKeys;
    }
}