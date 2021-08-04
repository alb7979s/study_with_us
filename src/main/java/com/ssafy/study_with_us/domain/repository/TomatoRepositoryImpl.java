package com.ssafy.study_with_us.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TomatoRepositoryImpl implements TomatoRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public TomatoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
