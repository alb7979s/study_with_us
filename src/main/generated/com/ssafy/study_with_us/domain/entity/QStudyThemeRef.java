package com.ssafy.study_with_us.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyThemeRef is a Querydsl query type for StudyThemeRef
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyThemeRef extends EntityPathBase<StudyThemeRef> {

    private static final long serialVersionUID = 2086673790L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyThemeRef studyThemeRef = new QStudyThemeRef("studyThemeRef");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStudy study;

    public final QTheme theme;

    public QStudyThemeRef(String variable) {
        this(StudyThemeRef.class, forVariable(variable), INITS);
    }

    public QStudyThemeRef(Path<? extends StudyThemeRef> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyThemeRef(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyThemeRef(PathMetadata metadata, PathInits inits) {
        this(StudyThemeRef.class, metadata, inits);
    }

    public QStudyThemeRef(Class<? extends StudyThemeRef> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study"), inits.get("study")) : null;
        this.theme = inits.isInitialized("theme") ? new QTheme(forProperty("theme")) : null;
    }

}

