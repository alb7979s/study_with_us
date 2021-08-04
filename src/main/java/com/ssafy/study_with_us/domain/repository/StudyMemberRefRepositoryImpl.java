package com.ssafy.study_with_us.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.QStudyMemberRef;
import com.ssafy.study_with_us.domain.entity.Study;
import com.ssafy.study_with_us.dto.IdReqDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QStudyMemberRef.*;

@Repository
public class StudyMemberRefRepositoryImpl implements StudyMemberRefRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public StudyMemberRefRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Object withdraw(IdReqDto params) {
        return jpaQueryFactory.delete(studyMemberRef)
                .where(studyMemberRef.member.id.eq(params.getMemberId()), studyMemberRef.study.id.eq(params.getStudyId()))
                .execute();
    }

    public List<Study> getByMemberId(Long memberId){
        return jpaQueryFactory.select(studyMemberRef.study).from(studyMemberRef).where(studyMemberRef.member.id.eq(memberId)).fetch();
    }

}
