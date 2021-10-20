package com.ssafy.study_with_us.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = -1041546412L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudy study = new QStudy("study");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStudyProfile profile;

    public final StringPath security = createString("security");

    public final StringPath studyIntro = createString("studyIntro");

    public final NumberPath<Long> studyLeader = createNumber("studyLeader", Long.class);

    public final StringPath studyName = createString("studyName");

    public final ListPath<StudyThemeRef, QStudyThemeRef> themes = this.<StudyThemeRef, QStudyThemeRef>createList("themes", StudyThemeRef.class, QStudyThemeRef.class, PathInits.DIRECT2);

    public QStudy(String variable) {
        this(Study.class, forVariable(variable), INITS);
    }

    public QStudy(Path<? extends Study> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudy(PathMetadata metadata, PathInits inits) {
        this(Study.class, metadata, inits);
    }

    public QStudy(Class<? extends Study> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QStudyProfile(forProperty("profile")) : null;
    }

}

