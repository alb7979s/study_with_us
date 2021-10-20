package com.ssafy.study_with_us.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberProfile is a Querydsl query type for MemberProfile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberProfile extends EntityPathBase<MemberProfile> {

    private static final long serialVersionUID = -1712467910L;

    public static final QMemberProfile memberProfile = new QMemberProfile("memberProfile");

    public final QProfile _super = new QProfile(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath image = _super.image;

    //inherited
    public final StringPath imageOrgName = _super.imageOrgName;

    //inherited
    public final StringPath path = _super.path;

    //inherited
    public final StringPath thumbnail = _super.thumbnail;

    public QMemberProfile(String variable) {
        super(MemberProfile.class, forVariable(variable));
    }

    public QMemberProfile(Path<? extends MemberProfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberProfile(PathMetadata metadata) {
        super(MemberProfile.class, metadata);
    }

}

