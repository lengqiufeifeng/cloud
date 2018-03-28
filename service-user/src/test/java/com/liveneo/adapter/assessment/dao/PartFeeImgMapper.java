package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.PartFeeImg;

public interface PartFeeImgMapper {
    int deleteByPrimaryKey(String caseNoImgId);

    int insert(PartFeeImg record);

    int insertSelective(PartFeeImg record);

    PartFeeImg selectByPrimaryKey(String caseNoImgId);

    int updateByPrimaryKeySelective(PartFeeImg record);

    int updateByPrimaryKey(PartFeeImg record);
}