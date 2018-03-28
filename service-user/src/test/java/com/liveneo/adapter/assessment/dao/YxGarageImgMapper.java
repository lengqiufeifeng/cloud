package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.YxGarageImg;

public interface YxGarageImgMapper {
    int deleteByPrimaryKey(String imgId);

    int insert(YxGarageImg record);

    int insertSelective(YxGarageImg record);

    YxGarageImg selectByPrimaryKey(String imgId);

    int updateByPrimaryKeySelective(YxGarageImg record);

    int updateByPrimaryKey(YxGarageImg record);
}