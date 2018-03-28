package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.RepairShop;

public interface RepairShopMapper {
    int deleteByPrimaryKey(String repairShopId);

    int insert(RepairShop record);

    int insertSelective(RepairShop record);

    RepairShop selectByPrimaryKey(String repairShopId);

    int updateByPrimaryKeySelective(RepairShop record);

    int updateByPrimaryKey(RepairShop record);
}