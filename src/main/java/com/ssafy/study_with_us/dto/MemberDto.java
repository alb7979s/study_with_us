package com.ssafy.study_with_us.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.study_with_us.domain.entity.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private Integer age;
    private String department;
    private Profile profile;

    @Builder
    public MemberDto(Long id, String email, String password, String username, Integer age, String department, Profile profile) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
        this.department = department;
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", profile=" + profile +
                '}';
    }
}