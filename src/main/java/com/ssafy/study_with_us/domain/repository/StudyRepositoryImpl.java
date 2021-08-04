package com.ssafy.study_with_us.domain.repository;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.*;
import com.ssafy.study_with_us.dto.StudyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QStudy.*;
import static com.ssafy.study_with_us.domain.entity.QStudyThemeRef.*;
import static com.ssafy.study_with_us.domain.entity.QTheme.theme;

@Repository
public class StudyRepositoryImpl implements StudyRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public StudyRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Theme> getThemes() {
        return jpaQueryFactory.selectFrom(theme).from(theme).fetch();
    }

    @Override
    public List<Theme> getThemes(Long studyId) {
        return jpaQueryFactory.select(studyThemeRef.theme).from(studyThemeRef).where(studyThemeRef.study.id.eq(studyId)).fetch();
    }

    @Override
    public StudyProfile getProfile(Long studyId) {
        return jpaQueryFactory.select(study.profile).from(study).where(study.id.eq(studyId)).fetchOne();
    }

    // 좀 더 효율적으로 가능할듯 후에 수정
    @Override
    public void remove(String theme, Long studyID) {
        jpaQueryFactory.delete(studyThemeRef).where(studyThemeRef.theme.themeName.eq(theme), studyThemeRef.study.id.eq(studyID)).execute();
    }

}
