package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.FeeInfo;

public interface FeeInfoMapper {
    int deleteByPrimaryKey(String feeInfoId);

    int insert(FeeInfo record);

    int insertSelective(FeeInfo record);

    FeeInfo selectByPrimaryKey(String feeInfoId);

    int updateByPrimaryKeySelective(FeeInfo record);

    int updateByPrimaryKey(FeeInfo record);
}