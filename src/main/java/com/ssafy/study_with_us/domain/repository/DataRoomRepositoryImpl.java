package com.ssafy.study_with_us.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.DataRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QDataRoom.dataRoom;


@Repository
public class DataRoomRepositoryImpl implements DataRoomRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public DataRoomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanExpression studyIdEq(Long studyId){
        return studyId == null ? null : dataRoom.study.id.eq(studyId);
    }
    private BooleanExpression memberIdEq(Long memberId){ return memberId == null ? null : dataRoom.member.id.eq(memberId); }
}
