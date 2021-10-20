package com.ssafy.study_with_us.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTomatoPlan is a Querydsl query type for TomatoPlan
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTomatoPlan extends EntityPathBase<TomatoPlan> {

    private static final long serialVersionUID = -873540696L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTomatoPlan tomatoPlan = new QTomatoPlan("tomatoPlan");

    public final NumberPath<Integer> goalTime = createNumber("goalTime", Integer.class);

    public final NumberPath<Integer> goalTomato = createNumber("goalTomato", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStudy study;

    public final DatePath<java.time.LocalDate> tomatoDate = createDate("tomatoDate", java.time.LocalDate.class);

    public QTomatoPlan(String variable) {
        this(TomatoPlan.class, forVariable(variable), INITS);
    }

    public QTomatoPlan(Path<? extends TomatoPlan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTomatoPlan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTomatoPlan(PathMetadata metadata, PathInits inits) {
        this(TomatoPlan.class, metadata, inits);
    }

    public QTomatoPlan(Class<? extends TomatoPlan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

