package com.ssafy.study_with_us.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyMemberRef is a Querydsl query type for StudyMemberRef
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyMemberRef extends EntityPathBase<StudyMemberRef> {

    private static final long serialVersionUID = 1587270309L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyMemberRef studyMemberRef = new QStudyMemberRef("studyMemberRef");

    public final BooleanPath connected = createBoolean("connected");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final StringPath nickname = createString("nickname");

    public final DateTimePath<java.time.LocalDateTime> recentlyConnectionTime = createDateTime("recentlyConnectionTime", java.time.LocalDateTime.class);

    public final QStudy study;

    public QStudyMemberRef(String variable) {
        this(StudyMemberRef.class, forVariable(variable), INITS);
    }

    public QStudyMemberRef(Path<? extends StudyMemberRef> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyMemberRef(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyMemberRef(PathMetadata metadata, PathInits inits) {
        this(StudyMemberRef.class, metadata, inits);
    }

    public QStudyMemberRef(Class<? extends StudyMemberRef> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

