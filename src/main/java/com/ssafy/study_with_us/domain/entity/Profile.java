package com.ssafy.study_with_us.domain.entity;


import com.ssafy.study_with_us.dto.ProfileDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    private String image;
    private String imageOrgName;
    private String thumbnail;
    private String path;

    public Profile() {
    }

    public Profile(Long id, String image, String imageOrgName, String thumbnail, String path) {
        this.id = id;
        this.image = image;
        this.imageOrgName = imageOrgName;
        this.thumbnail = thumbnail;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", imageOrgName='" + imageOrgName + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
    public ProfileDto entityToDto(){
        return ProfileDto.builder().id(id).image(image).imageOrgName(imageOrgName)
                .thumbnail(thumbnail).path(path).build();
    }
}
