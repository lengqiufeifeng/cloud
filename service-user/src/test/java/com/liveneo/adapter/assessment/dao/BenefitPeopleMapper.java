package com.liveneo.adapter.assessment.dao;

import com.liveneo.adapter.assessment.model.BenefitPeople;

public interface BenefitPeopleMapper {
    int deleteByPrimaryKey(String benefitPeopleId);

    int insert(BenefitPeople record);

    int insertSelective(BenefitPeople record);

    BenefitPeople selectByPrimaryKey(String benefitPeopleId);

    int updateByPrimaryKeySelective(BenefitPeople record);

    int updateByPrimaryKey(BenefitPeople record);
}