package com.ssafy.study_with_us.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.QStudyThemeRef;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QStudyThemeRef.*;

@Repository
public class StudyThemeRefRepositoryImpl implements StudyThemeRefRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public StudyThemeRefRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Long> searchStudyByThemes(List<String> themes) {
        return jpaQueryFactory.selectDistinct(studyThemeRef.study.id).from(studyThemeRef)
                .where(studyThemeRef.theme.themeName.in(themes)).fetch();
    }
}
