package com.liveneo.adapter.assessment.model;

import java.io.Serializable;
import java.util.Date;

public class YxGarage implements Serializable {
    private String rfId;

    private String rfName;

    private String rfShortName;

    private String rfType;

    private String rfTanager;

    private String rfManPhone;

    private String rfContact;

    private String rfConPhone;

    private String rfAddr;

    private String rfLicense;

    private String rfProvince;

    private String rfDity;

    private String rfDis;

    private String rfLat;

    private String rfLon;

    private String rfBrandName;

    private String delFlag;

    private String existEt;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId == null ? null : rfId.trim();
    }

    public String getRfName() {
        return rfName;
    }

    public void setRfName(String rfName) {
        this.rfName = rfName == null ? null : rfName.trim();
    }

    public String getRfShortName() {
        return rfShortName;
    }

    public void setRfShortName(String rfShortName) {
        this.rfShortName = rfShortName == null ? null : rfShortName.trim();
    }

    public String getRfType() {
        return rfType;
    }

    public void setRfType(String rfType) {
        this.rfType = rfType == null ? null : rfType.trim();
    }

    public String getRfTanager() {
        return rfTanager;
    }

    public void setRfTanager(String rfTanager) {
        this.rfTanager = rfTanager == null ? null : rfTanager.trim();
    }

    public String getRfManPhone() {
        return rfManPhone;
    }

    public void setRfManPhone(String rfManPhone) {
        this.rfManPhone = rfManPhone == null ? null : rfManPhone.trim();
    }

    public String getRfContact() {
        return rfContact;
    }

    public void setRfContact(String rfContact) {
        this.rfContact = rfContact == null ? null : rfContact.trim();
    }

    public String getRfConPhone() {
        return rfConPhone;
    }

    public void setRfConPhone(String rfConPhone) {
        this.rfConPhone = rfConPhone == null ? null : rfConPhone.trim();
    }

    public String getRfAddr() {
        return rfAddr;
    }

    public void setRfAddr(String rfAddr) {
        this.rfAddr = rfAddr == null ? null : rfAddr.trim();
    }

    public String getRfLicense() {
        return rfLicense;
    }

    public void setRfLicense(String rfLicense) {
        this.rfLicense = rfLicense == null ? null : rfLicense.trim();
    }

    public String getRfProvince() {
        return rfProvince;
    }

    public void setRfProvince(String rfProvince) {
        this.rfProvince = rfProvince == null ? null : rfProvince.trim();
    }

    public String getRfDity() {
        return rfDity;
    }

    public void setRfDity(String rfDity) {
        this.rfDity = rfDity == null ? null : rfDity.trim();
    }

    public String getRfDis() {
        return rfDis;
    }

    public void setRfDis(String rfDis) {
        this.rfDis = rfDis == null ? null : rfDis.trim();
    }

    public String getRfLat() {
        return rfLat;
    }

    public void setRfLat(String rfLat) {
        this.rfLat = rfLat == null ? null : rfLat.trim();
    }

    public String getRfLon() {
        return rfLon;
    }

    public void setRfLon(String rfLon) {
        this.rfLon = rfLon == null ? null : rfLon.trim();
    }

    public String getRfBrandName() {
        return rfBrandName;
    }

    public void setRfBrandName(String rfBrandName) {
        this.rfBrandName = rfBrandName == null ? null : rfBrandName.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getExistEt() {
        return existEt;
    }

    public void setExistEt(String existEt) {
        this.existEt = existEt == null ? null : existEt.trim();
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