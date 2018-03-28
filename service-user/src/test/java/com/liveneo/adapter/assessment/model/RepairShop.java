package com.liveneo.adapter.assessment.model;

import java.io.Serializable;
import java.util.Date;

public class RepairShop implements Serializable {
    private String repairShopId;

    private String repairShopName;

    private String repairShopCode;

    private String repairShopType;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getRepairShopId() {
        return repairShopId;
    }

    public void setRepairShopId(String repairShopId) {
        this.repairShopId = repairShopId == null ? null : repairShopId.trim();
    }

    public String getRepairShopName() {
        return repairShopName;
    }

    public void setRepairShopName(String repairShopName) {
        this.repairShopName = repairShopName == null ? null : repairShopName.trim();
    }

    public String getRepairShopCode() {
        return repairShopCode;
    }

    public void setRepairShopCode(String repairShopCode) {
        this.repairShopCode = repairShopCode == null ? null : repairShopCode.trim();
    }

    public String getRepairShopType() {
        return repairShopType;
    }

    public void setRepairShopType(String repairShopType) {
        this.repairShopType = repairShopType == null ? null : repairShopType.trim();
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