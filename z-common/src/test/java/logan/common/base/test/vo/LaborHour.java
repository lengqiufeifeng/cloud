package logan.common.base.test.vo;

import java.util.List;

/**
 * @author zhoupan
 */
public class LaborHour {

    /**
     * 工时名称
     * this is laborHourName
     * Y
     */
    private String laborHourName;

    /**
     * 工时数
     * this is laborHour
     * N
     */
    private String laborHour;

    /**
     * 时薪
     * this is hourlyPay
     * N
     */
    private String hourlyPay;

    /**
     * 照片视频路径
     * this is ossKeys
     * N
     */
    private List<String> ossKeys;

    public void setLaborHourName(String laborHourName) {
        this.laborHourName = laborHourName;
    }

    public String getLaborHourName() {
        return this.laborHourName;
    }

    public void setLaborHour(String laborHour) {
        this.laborHour = laborHour;
    }

    public String getLaborHour() {
        return this.laborHour;
    }

    public void setHourlyPay(String hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public String getHourlyPay() {
        return this.hourlyPay;
    }

    public void setOssKeys(List<String> ossKeys) {
        this.ossKeys = ossKeys;
    }

    public List<String> getOssKeys() {
        return this.ossKeys;
    }
}