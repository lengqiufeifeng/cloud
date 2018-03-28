package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.Part;

public interface PartMapper {
    int deleteByPrimaryKey(String partId);

    int insert(Part record);

    int insertSelective(Part record);

    Part selectByPrimaryKey(String partId);

    int updateByPrimaryKeySelective(Part record);

    int updateByPrimaryKey(Part record);
}