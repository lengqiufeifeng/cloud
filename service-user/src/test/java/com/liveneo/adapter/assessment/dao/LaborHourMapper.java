package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.LaborHour;

public interface LaborHourMapper {
    int deleteByPrimaryKey(String laborHourId);

    int insert(LaborHour record);

    int insertSelective(LaborHour record);

    LaborHour selectByPrimaryKey(String laborHourId);

    int updateByPrimaryKeySelective(LaborHour record);

    int updateByPrimaryKey(LaborHour record);
}