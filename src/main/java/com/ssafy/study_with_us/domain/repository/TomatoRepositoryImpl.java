package com.ssafy.study_with_us.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.QTomato;
import com.ssafy.study_with_us.domain.entity.Tomato;
import com.ssafy.study_with_us.dto.TomatoDto;
import org.springframework.stereotype.Repository;

import static com.ssafy.study_with_us.domain.entity.QTomato.*;

@Repository
public class TomatoRepositoryImpl implements TomatoRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public TomatoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    // null 처리?
    @Override
    public Tomato addTomato(TomatoDto params) {
        return jpaQueryFactory.selectFrom(QTomato.tomato)
                .where(QTomato.tomato.member.id.eq(params.getMemberId())
                        .and(QTomato.tomato.study.id.eq(params.getStudyId()))
                        .and(QTomato.tomato.tomatoDate.eq(params.getDate())))
                .fetchOne();
    }
}
