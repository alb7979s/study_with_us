package com.ssafy.study_with_us.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.Tomato;
import com.ssafy.study_with_us.dto.StudyDto;
import com.ssafy.study_with_us.dto.TomatoDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QTomato.tomato;

@Repository
public class TomatoRepositoryImpl implements TomatoRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public TomatoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Tomato searchTomato(TomatoDto params) {
        return jpaQueryFactory.selectFrom(tomato)
                .where(memberIdEq(params.getMemberId()), studyIdEq(params.getStudyId()), tomatoDateEq(params.getDate()))
                .fetchOne();
    }

    @Override
    public Integer getRelevantSum(TomatoDto params) {
        return jpaQueryFactory.select(tomato.tomatoCount.sum()).from(tomato).where(studyIdEq(params.getStudyId())).fetchOne();
    }

    @Override
    public Integer getRelevantSum(Long memberId) {
        return jpaQueryFactory.select(tomato.tomatoCount.sum()).from(tomato).where(memberIdEq(memberId)).fetchOne();
    }

    @Override
    public List<Tomato> getTomatoes(Long memberId) {
        return jpaQueryFactory.selectFrom(tomato).where(memberIdEq(memberId)).fetch();
    }

    @Override
    public List<Tomato> getTomatoes(TomatoDto params) {
        return jpaQueryFactory.selectFrom(tomato).where(studyIdEq(params.getStudyId())).fetch();
    }

    @Override
    public Integer getTotalSum() {
        return jpaQueryFactory.select(tomato.tomatoCount.sum()).from(tomato).fetchOne();
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
