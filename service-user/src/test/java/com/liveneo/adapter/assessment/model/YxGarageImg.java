package com.liveneo.adapter.assessment.model;

import java.io.Serializable;
import java.util.Date;

public class YxGarageImg implements Serializable {
    private String imgId;

    private String rfId;

    private String imgOsskey;

    private String photoOrder;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId == null ? null : imgId.trim();
    }

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId == null ? null : rfId.trim();
    }

    public String getImgOsskey() {
        return imgOsskey;
    }

    public void setImgOsskey(String imgOsskey) {
        this.imgOsskey = imgOsskey == null ? null : imgOsskey.trim();
    }

    public String getPhotoOrder() {
        return photoOrder;
    }

    public void setPhotoOrder(String photoOrder) {
        this.photoOrder = photoOrder == null ? null : photoOrder.trim();
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