package com.ssafy.study_with_us.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.StudyProfile;
import com.ssafy.study_with_us.domain.entity.Theme;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QStudy.study;
import static com.ssafy.study_with_us.domain.entity.QStudyThemeRef.studyThemeRef;
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

    @Override
    public void remove(String theme, Long studyId) {
        jpaQueryFactory.delete(studyThemeRef).where(themeNameEq(theme), studyIdEq(studyId)).execute();
    }

    private BooleanExpression themeNameEq(String theme){
        return theme == null ? null : studyThemeRef.theme.themeName.eq(theme);
    }
    private BooleanExpression studyIdEq(Long studyId){
        return studyId == null ? null : studyThemeRef.study.id.eq(studyId);
    }

}
