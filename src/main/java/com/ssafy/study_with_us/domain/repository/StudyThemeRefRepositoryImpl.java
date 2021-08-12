package com.ssafy.study_with_us.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QStudyThemeRef.studyThemeRef;

@Repository
public class StudyThemeRefRepositoryImpl implements StudyThemeRefRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public StudyThemeRefRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Long> searchStudyByThemes(List<String> themes, Integer page) {
        return jpaQueryFactory.selectDistinct(studyThemeRef.study.id).from(studyThemeRef)
                .where(themeNameIn(themes)).offset((page-1)*6).limit(6).fetch();
    }
    private BooleanExpression themeNameIn(List<String> themes){
        return themes == null ? null : studyThemeRef.theme.themeName.in(themes);
    }
}
