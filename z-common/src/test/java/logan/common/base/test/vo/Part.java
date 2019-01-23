package logan.common.base.test.vo;

import java.util.List;

/**
 * @author zhoupan
 */
public class Part {

    /**
     * 零配件名称
     * this is partName
     * Y
     */
    private String partName;

    /**
     * 金额
     * this is money
     * Y
     */
    private String money;

    /**
     * 复勘
     * this is againSurvey
     * Y
     */
    private String againSurvey;

    /**
     * 回收
     * this is recycle
     * Y
     */
    private String recycle;

    /**
     * 照片视频路径
     * this is ossKeys
     * N
     */
    private List<String> ossKeys;

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartName() {
        return this.partName;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return this.money;
    }

    public void setAgainSurvey(String againSurvey) {
        this.againSurvey = againSurvey;
    }

    public String getAgainSurvey() {
        return this.againSurvey;
    }

    public void setRecycle(String recycle) {
        this.recycle = recycle;
    }

    public String getRecycle() {
        return this.recycle;
    }

    public void setOssKeys(List<String> ossKeys) {
        this.ossKeys = ossKeys;
    }

    public List<String> getOssKeys() {
        return this.ossKeys;
    }
}