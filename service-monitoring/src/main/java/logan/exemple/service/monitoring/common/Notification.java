package logan.exemple.service.monitoring.common;

import java.io.File;
import java.util.List;

/**
 * Created by qiufeng on 2017/9/22.
 */
public class Notification {
    /**
     * 通知类型 0：系统启动通知，1：系统异常通知,2：自定义通知
     */
    public int ntcType;
    /**
     * 0 : 短信；1：email；2：微信；3：支付宝；4：钉钉；5：三方推送；6：自定义推送
     */
    public Integer[] msgTypes;
    public String title;
    public String url;
    public String contentMsg;
    public List<File> files;
    public List<NtcPersonnel> ntcPersonnels;
}
