package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.Rescue;

public interface RescueMapper {
    int deleteByPrimaryKey(String rescueId);

    int insert(Rescue record);

    int insertSelective(Rescue record);

    Rescue selectByPrimaryKey(String rescueId);

    int updateByPrimaryKeySelective(Rescue record);

    int updateByPrimaryKey(Rescue record);
}