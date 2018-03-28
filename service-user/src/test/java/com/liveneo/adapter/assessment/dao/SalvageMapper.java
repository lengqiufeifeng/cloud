package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.Salvage;

public interface SalvageMapper {
    int deleteByPrimaryKey(String salvageId);

    int insert(Salvage record);

    int insertSelective(Salvage record);

    Salvage selectByPrimaryKey(String salvageId);

    int updateByPrimaryKeySelective(Salvage record);

    int updateByPrimaryKey(Salvage record);
}