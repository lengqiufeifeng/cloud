package com.liveneo.adapter.assessment.model;

import java.io.Serializable;
import java.util.Date;

public class FeeInfo implements Serializable {
    private String feeInfoId;

    private String orgCode;

    private String caseNo;

    private String lossId;

    private String partFeeCode;

    private String partFeeName;

    private String partFeePh;

    private String vin;

    private String brandType;

    private String engineNo;

    private String driverName;

    private String partsTotalMoney;

    private String laborHoursTotalMoney;

    private String ingredientsTotalMoney;

    private String benefitPeoplesTotalMoney;

    private String feeAmount;

    private String rescuesTotalMoney;

    private String salvagesTotalMoney;

    private String totalFeeAmount;

    private String repairShopName;

    private String repairShopCode;

    private String priceType;

    private String accidentCause;

    private String totalLoss;

    private String remarks;

    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getFeeInfoId() {
        return feeInfoId;
    }

    public void setFeeInfoId(String feeInfoId) {
        this.feeInfoId = feeInfoId == null ? null : feeInfoId.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
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

    public String getPartFeeCode() {
        return partFeeCode;
    }

    public void setPartFeeCode(String partFeeCode) {
        this.partFeeCode = partFeeCode == null ? null : partFeeCode.trim();
    }

    public String getPartFeeName() {
        return partFeeName;
    }

    public void setPartFeeName(String partFeeName) {
        this.partFeeName = partFeeName == null ? null : partFeeName.trim();
    }

    public String getPartFeePh() {
        return partFeePh;
    }

    public void setPartFeePh(String partFeePh) {
        this.partFeePh = partFeePh == null ? null : partFeePh.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType == null ? null : brandType.trim();
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo == null ? null : engineNo.trim();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getPartsTotalMoney() {
        return partsTotalMoney;
    }

    public void setPartsTotalMoney(String partsTotalMoney) {
        this.partsTotalMoney = partsTotalMoney == null ? null : partsTotalMoney.trim();
    }

    public String getLaborHoursTotalMoney() {
        return laborHoursTotalMoney;
    }

    public void setLaborHoursTotalMoney(String laborHoursTotalMoney) {
        this.laborHoursTotalMoney = laborHoursTotalMoney == null ? null : laborHoursTotalMoney.trim();
    }

    public String getIngredientsTotalMoney() {
        return ingredientsTotalMoney;
    }

    public void setIngredientsTotalMoney(String ingredientsTotalMoney) {
        this.ingredientsTotalMoney = ingredientsTotalMoney == null ? null : ingredientsTotalMoney.trim();
    }

    public String getBenefitPeoplesTotalMoney() {
        return benefitPeoplesTotalMoney;
    }

    public void setBenefitPeoplesTotalMoney(String benefitPeoplesTotalMoney) {
        this.benefitPeoplesTotalMoney = benefitPeoplesTotalMoney == null ? null : benefitPeoplesTotalMoney.trim();
    }

    public String getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount == null ? null : feeAmount.trim();
    }

    public String getRescuesTotalMoney() {
        return rescuesTotalMoney;
    }

    public void setRescuesTotalMoney(String rescuesTotalMoney) {
        this.rescuesTotalMoney = rescuesTotalMoney == null ? null : rescuesTotalMoney.trim();
    }

    public String getSalvagesTotalMoney() {
        return salvagesTotalMoney;
    }

    public void setSalvagesTotalMoney(String salvagesTotalMoney) {
        this.salvagesTotalMoney = salvagesTotalMoney == null ? null : salvagesTotalMoney.trim();
    }

    public String getTotalFeeAmount() {
        return totalFeeAmount;
    }

    public void setTotalFeeAmount(String totalFeeAmount) {
        this.totalFeeAmount = totalFeeAmount == null ? null : totalFeeAmount.trim();
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

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType == null ? null : priceType.trim();
    }

    public String getAccidentCause() {
        return accidentCause;
    }

    public void setAccidentCause(String accidentCause) {
        this.accidentCause = accidentCause == null ? null : accidentCause.trim();
    }

    public String getTotalLoss() {
        return totalLoss;
    }

    public void setTotalLoss(String totalLoss) {
        this.totalLoss = totalLoss == null ? null : totalLoss.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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