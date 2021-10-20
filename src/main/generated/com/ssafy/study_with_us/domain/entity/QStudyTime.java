package com.ssafy.study_with_us.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyTime is a Querydsl query type for StudyTime
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyTime extends EntityPathBase<StudyTime> {

    private static final long serialVersionUID = -1990639551L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyTime studyTime1 = new QStudyTime("studyTime1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final DatePath<java.time.LocalDate> studyDate = createDate("studyDate", java.time.LocalDate.class);

    public final NumberPath<Long> studyTime = createNumber("studyTime", Long.class);

    public QStudyTime(String variable) {
        this(StudyTime.class, forVariable(variable), INITS);
    }

    public QStudyTime(Path<? extends StudyTime> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyTime(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyTime(PathMetadata metadata, PathInits inits) {
        this(StudyTime.class, metadata, inits);
    }

    public QStudyTime(Class<? extends StudyTime> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

