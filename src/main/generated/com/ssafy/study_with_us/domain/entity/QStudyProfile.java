package com.ssafy.study_with_us.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyProfile is a Querydsl query type for StudyProfile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyProfile extends EntityPathBase<StudyProfile> {

    private static final long serialVersionUID = -1524811851L;

    public static final QStudyProfile studyProfile = new QStudyProfile("studyProfile");

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

    public QStudyProfile(String variable) {
        super(StudyProfile.class, forVariable(variable));
    }

    public QStudyProfile(Path<? extends StudyProfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyProfile(PathMetadata metadata) {
        super(StudyProfile.class, metadata);
    }

}

