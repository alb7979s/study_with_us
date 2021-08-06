package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Tomato;
import com.ssafy.study_with_us.dto.StudyDto;
import com.ssafy.study_with_us.dto.TomatoDto;

import java.util.List;

public interface TomatoRepositoryCustom {
    Tomato addTomato(TomatoDto params);
    Integer getTotalSum();
    Integer getRelevantSum(TomatoDto params);
    Integer getRelevantSum(Long memberId);
    List<Tomato> getTomatoes(Long memberId);
    List<Tomato> getTomatoes(TomatoDto params);
}
