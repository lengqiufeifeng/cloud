package logan.exemple.service.monitoring.model;

import java.io.Serializable;
import java.util.Date;

public class SysNotification implements Serializable {
    private Integer ntId;

    private String sysName;

    private String sysCode;

    private String serviceName;

    private String serviceCode;

    private String nctName;

    private Byte nctType;

    private String nctUri;

    private String nctParameters;

    private Byte nctStatus;

    private Date updataTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getNtId() {
        return ntId;
    }

    public void setNtId(Integer ntId) {
        this.ntId = ntId;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? null : serviceCode.trim();
    }

    public String getNctName() {
        return nctName;
    }

    public void setNctName(String nctName) {
        this.nctName = nctName == null ? null : nctName.trim();
    }

    public Byte getNctType() {
        return nctType;
    }

    public void setNctType(Byte nctType) {
        this.nctType = nctType;
    }

    public String getNctUri() {
        return nctUri;
    }

    public void setNctUri(String nctUri) {
        this.nctUri = nctUri == null ? null : nctUri.trim();
    }

    public String getNctParameters() {
        return nctParameters;
    }

    public void setNctParameters(String nctParameters) {
        this.nctParameters = nctParameters == null ? null : nctParameters.trim();
    }

    public Byte getNctStatus() {
        return nctStatus;
    }

    public void setNctStatus(Byte nctStatus) {
        this.nctStatus = nctStatus;
    }

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}