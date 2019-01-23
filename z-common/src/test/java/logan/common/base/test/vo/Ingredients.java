package logan.common.base.test.vo;

import java.util.List;

/**
 * @author zhoupan
 */
public class Ingredients {

    /**
     * 辅料名称
     * this is ingredientsName
     * Y
     */
    private String ingredientsName;

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

    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }

    public String getIngredientsName() {
        return this.ingredientsName;
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