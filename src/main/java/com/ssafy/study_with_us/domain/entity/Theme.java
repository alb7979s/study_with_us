package com.ssafy.study_with_us.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Theme {
    @Id
    @Column(name = "theme_name")
    private String themeName;

    public Theme() {
    }

    @Builder
    public Theme(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "themeName='" + themeName + '\'' +
                '}';
    }
}
