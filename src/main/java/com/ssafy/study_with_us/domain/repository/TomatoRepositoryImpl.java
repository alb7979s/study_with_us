package com.ssafy.study_with_us.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.QTomato;
import com.ssafy.study_with_us.domain.entity.Tomato;
import com.ssafy.study_with_us.dto.TomatoDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import static com.ssafy.study_with_us.domain.entity.QTomato.*;

@Repository
public class TomatoRepositoryImpl implements TomatoRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public TomatoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Tomato addTomato(TomatoDto params) {
        return jpaQueryFactory.selectFrom(tomato)
                .where(memberIdEq(params.getMemberId()), studyIdEq(params.getStudyId()), tomatoDateEq(params.getDate()))
                .fetchOne();
    }

    private BooleanExpression memberIdEq(Long memberId){
        return memberId == null ? null : tomato.member.id.eq(memberId);
    }
    private BooleanExpression studyIdEq(Long studyId){
        return studyId == null ? null : tomato.study.id.eq(studyId);
    }
    private BooleanExpression tomatoDateEq(LocalDate date){
        return date == null ? null : tomato.tomatoDate.eq(date);
    }
}
