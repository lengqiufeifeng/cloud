package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.Ingredients;

public interface IngredientsMapper {
    int deleteByPrimaryKey(String ingredientsId);

    int insert(Ingredients record);

    int insertSelective(Ingredients record);

    Ingredients selectByPrimaryKey(String ingredientsId);

    int updateByPrimaryKeySelective(Ingredients record);

    int updateByPrimaryKey(Ingredients record);
}