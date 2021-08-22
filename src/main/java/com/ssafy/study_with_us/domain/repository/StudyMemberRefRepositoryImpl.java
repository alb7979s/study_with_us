package com.ssafy.study_with_us.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.Study;
import com.ssafy.study_with_us.domain.entity.StudyMemberRef;
import com.ssafy.study_with_us.dto.IdReqDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QStudyMemberRef.studyMemberRef;

@Repository
public class StudyMemberRefRepositoryImpl implements StudyMemberRefRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final Long recentlyPagingSize;

    public StudyMemberRefRepositoryImpl(JPAQueryFactory jpaQueryFactory, @Value("${recently.paging.size}")Long recentlyPagingSize) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.recentlyPagingSize = recentlyPagingSize;
    }

    @Override
    public Object withdraw(IdReqDto params) {
        return jpaQueryFactory.delete(studyMemberRef)
                .where(memberIdEq(params.getMemberId()), studyIdEq(params.getStudyId()))
                .execute();
    }

    @Override
    public StudyMemberRef getStudyMember(Long memberId, Long studyId) {
        return jpaQueryFactory.selectFrom(studyMemberRef).where(memberIdEq(memberId), studyIdEq(studyId)).fetchOne();
    }

    @Override
    public List<StudyMemberRef> getRecentlyStudies(Long memberId) {
        return jpaQueryFactory.selectFrom(studyMemberRef).where(memberIdEq(memberId), studyMemberRef.recentlyConnectionTime.isNotNull()).orderBy(studyMemberRef.recentlyConnectionTime.desc()).limit(recentlyPagingSize).fetch();
    }

    @Override
    public List<StudyMemberRef> getConnectionList(Long studyId) {
        return jpaQueryFactory.selectFrom(studyMemberRef).where(studyIdEq(studyId), connectedEq(true)).fetch();
    }

    private BooleanExpression studyIdEq(Long studyId){
        return studyId == null ? null : studyMemberRef.study.id.eq(studyId);
    }
    private BooleanExpression memberIdEq(Long memberId){
        return memberId == null ? null : studyMemberRef.member.id.eq(memberId);
    }
    private BooleanExpression connectedEq(Boolean connected){
        return connected == null ? null : studyMemberRef.connected.eq(connected);
    }
}
