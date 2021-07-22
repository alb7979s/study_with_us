package com.ssafy.study_with_us.domain.entity;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn    //하위 테이블 구분 컬럼 생성(DTYPE)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @Column
    private String image;
    @Column
    private String thumbnail;
    @Column
    private String path;

}
