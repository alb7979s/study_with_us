package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


// response로 entity 안넘기려고 만듦.. 후에 시간 나면 정리하기
@Getter
@ToString
@NoArgsConstructor
public class MemberResDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private Integer age;
    private String department;
    private LocalDateTime studytime;
    private ProfileDto profile;

    @Builder
    public MemberResDto(Long id, String email, String password, String username, Integer age, String department, LocalDateTime studytime, ProfileDto profile) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
        this.department = department;
        this.studytime = studytime;
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "MemberResDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", studytime=" + studytime +
                ", profile=" + profile +
                '}';
    }
}
