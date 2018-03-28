package com.liveneo.adapter.assessment.model;

import java.io.Serializable;
import java.util.Date;

public class BenefitPeople implements Serializable {
    private String benefitPeopleId;

    private String caseNo;

    private String lossId;

    private String benefitPeopleName;

    private String price;

    private String repairShopName;

    private String repairShopCode;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getBenefitPeopleId() {
        return benefitPeopleId;
    }

    public void setBenefitPeopleId(String benefitPeopleId) {
        this.benefitPeopleId = benefitPeopleId == null ? null : benefitPeopleId.trim();
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

    public String getBenefitPeopleName() {
        return benefitPeopleName;
    }

    public void setBenefitPeopleName(String benefitPeopleName) {
        this.benefitPeopleName = benefitPeopleName == null ? null : benefitPeopleName.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
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