package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.YxGarage;

public interface YxGarageMapper {
    int deleteByPrimaryKey(String rfId);

    int insert(YxGarage record);

    int insertSelective(YxGarage record);

    YxGarage selectByPrimaryKey(String rfId);

    int updateByPrimaryKeySelective(YxGarage record);

    int updateByPrimaryKey(YxGarage record);
}