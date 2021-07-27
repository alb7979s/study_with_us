package com.ssafy.study_with_us.dto;

import com.ssafy.study_with_us.domain.entity.Member;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private Integer age;
    private String department;
    private LocalDateTime studytime;
    private ProfileDto profile;

//    public Member toEntity(){
//        return Member.builder()
//                .id(id)
//                .email(email)
//                .password(password)
//                .name(name)
//                .nickname(nickname)
//                .age(age)
//                .group(group)
//                .build();
//    }

    @Builder
    public MemberDto(Long id, String email, String password, String username, Integer age, String department, LocalDateTime studytime, ProfileDto profile) {
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
        return "MemberDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", age='" + age + '\'' +
                ", department='" + department + '\'' +
                ", studytime='" + studytime + '\'' +
                '}';
    }
}