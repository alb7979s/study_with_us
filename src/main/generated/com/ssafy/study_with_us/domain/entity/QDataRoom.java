package com.ssafy.study_with_us.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDataRoom is a Querydsl query type for DataRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataRoom extends EntityPathBase<DataRoom> {

    private static final long serialVersionUID = -1952916934L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDataRoom dataRoom = new QDataRoom("dataRoom");

    public final StringPath content = createString("content");

    public final ListPath<FileEntity, QFileEntity> files = this.<FileEntity, QFileEntity>createList("files", FileEntity.class, QFileEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QStudy study;

    public final StringPath subject = createString("subject");

    public QDataRoom(String variable) {
        this(DataRoom.class, forVariable(variable), INITS);
    }

    public QDataRoom(Path<? extends DataRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDataRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDataRoom(PathMetadata metadata, PathInits inits) {
        this(DataRoom.class, metadata, inits);
    }

    public QDataRoom(Class<? extends DataRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

