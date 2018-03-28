package com.liveneo.adapter.assessment.model;

import java.io.Serializable;
import java.util.Date;

public class Rescue implements Serializable {
    private String rescueId;

    private String caseNo;

    private String lossId;

    private String rescueName;

    private String price;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getRescueId() {
        return rescueId;
    }

    public void setRescueId(String rescueId) {
        this.rescueId = rescueId == null ? null : rescueId.trim();
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

    public String getRescueName() {
        return rescueName;
    }

    public void setRescueName(String rescueName) {
        this.rescueName = rescueName == null ? null : rescueName.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
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