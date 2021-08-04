package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Tomato;
import com.ssafy.study_with_us.dto.TomatoDto;

public interface TomatoRepositoryCustom {
    Tomato addTomato(TomatoDto params);
}
