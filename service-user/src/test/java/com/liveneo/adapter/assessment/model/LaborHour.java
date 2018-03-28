package com.liveneo.adapter.assessment.model;

import java.io.Serializable;
import java.util.Date;

public class LaborHour implements Serializable {
    private String laborHourId;

    private String caseNo;

    private String lossId;

    private String laborHourName;

    private String laborHour;

    private String hourlyPay;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getLaborHourId() {
        return laborHourId;
    }

    public void setLaborHourId(String laborHourId) {
        this.laborHourId = laborHourId == null ? null : laborHourId.trim();
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    public String getLossId() {
        return lossId;
    }

    public void setLossId(String lossId) {
        this.lossId = lossId == null ? null : lossId.trim();
    }

    public String getLaborHourName() {
        return laborHourName;
    }

    public void setLaborHourName(String laborHourName) {
        this.laborHourName = laborHourName == null ? null : laborHourName.trim();
    }

    public String getLaborHour() {
        return laborHour;
    }

    public void setLaborHour(String laborHour) {
        this.laborHour = laborHour == null ? null : laborHour.trim();
    }

    public String getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(String hourlyPay) {
        this.hourlyPay = hourlyPay == null ? null : hourlyPay.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}