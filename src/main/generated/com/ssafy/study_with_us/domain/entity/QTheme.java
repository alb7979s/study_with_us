package com.ssafy.study_with_us.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTheme is a Querydsl query type for Theme
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTheme extends EntityPathBase<Theme> {

    private static final long serialVersionUID = -1040995500L;

    public static final QTheme theme = new QTheme("theme");

    public final StringPath themeName = createString("themeName");

    public QTheme(String variable) {
        super(Theme.class, forVariable(variable));
    }

    public QTheme(Path<? extends Theme> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTheme(PathMetadata metadata) {
        super(Theme.class, metadata);
    }

}

