package com.ssafy.study_with_us.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTomato is a Querydsl query type for Tomato
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTomato extends EntityPathBase<Tomato> {

    private static final long serialVersionUID = 2095569887L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTomato tomato = new QTomato("tomato");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QStudy study;

    public final NumberPath<Integer> tomatoCount = createNumber("tomatoCount", Integer.class);

    public final DatePath<java.time.LocalDate> tomatoDate = createDate("tomatoDate", java.time.LocalDate.class);

    public QTomato(String variable) {
        this(Tomato.class, forVariable(variable), INITS);
    }

    public QTomato(Path<? extends Tomato> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTomato(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTomato(PathMetadata metadata, PathInits inits) {
        this(Tomato.class, metadata, inits);
    }

    public QTomato(Class<? extends Tomato> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

