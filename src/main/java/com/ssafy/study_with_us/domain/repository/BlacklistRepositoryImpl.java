package com.ssafy.study_with_us.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static com.ssafy.study_with_us.domain.entity.QBlacklist.blacklist;

@Repository
public class BlacklistRepositoryImpl implements BlacklistRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public BlacklistRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public void delete(Long studyId, Long memberId) {
        jpaQueryFactory.delete(blacklist).where(studyIdEq(studyId), memberIdEq(memberId)).execute();
    }

    private BooleanExpression studyIdEq(Long studyId){
        return studyId == null ? null : blacklist.study.id.eq(studyId);
    }
    private BooleanExpression memberIdEq(Long memberId){
        return memberId == null ? null : blacklist.member.id.eq(memberId);
    }
}
