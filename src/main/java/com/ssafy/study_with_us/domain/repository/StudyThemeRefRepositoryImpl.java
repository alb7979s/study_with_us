package com.ssafy.study_with_us.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QStudyThemeRef.studyThemeRef;

@Repository
public class StudyThemeRefRepositoryImpl implements StudyThemeRefRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final Long pagingSize;

    public StudyThemeRefRepositoryImpl(JPAQueryFactory jpaQueryFactory, @Value("${paging.size}") Long pagingSize) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.pagingSize = pagingSize;
    }

    @Override
    public List<Long> searchStudyByThemes(List<String> themes, Integer page) {
        return jpaQueryFactory.selectDistinct(studyThemeRef.study.id).from(studyThemeRef)
                .where(themeNameIn(themes)).offset((page-1)*pagingSize).limit(pagingSize).fetch();
    }

    @Override
    public Long countStudyByThemes(List<String> themes) {
        return jpaQueryFactory.selectDistinct(studyThemeRef.study.id).from(studyThemeRef).where(themeNameIn(themes)).fetchCount();
    }

    private BooleanExpression themeNameIn(List<String> themes){
        return themes == null ? null : studyThemeRef.theme.themeName.in(themes);
    }
}
