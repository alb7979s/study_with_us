package com.ssafy.study_with_us.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.DataRoom;
import com.ssafy.study_with_us.domain.entity.QFileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QDataRoom.dataRoom;
import static com.ssafy.study_with_us.domain.entity.QFileEntity.*;


@Repository
public class DataRoomRepositoryImpl implements DataRoomRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public DataRoomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public Long deleteFiles(Long dataRoomId) {
        return jpaQueryFactory.delete(fileEntity).where(dataRoomIdEq(dataRoomId)).execute();
    }

    private BooleanExpression studyIdEq(Long studyId){
        return studyId == null ? null : dataRoom.study.id.eq(studyId);
    }
    private BooleanExpression memberIdEq(Long memberId){ return memberId == null ? null : dataRoom.member.id.eq(memberId); }
    private BooleanExpression dataRoomIdEq(Long dataRoomId){ return dataRoomId == null ? null : dataRoom.id.eq(dataRoomId); }
}
