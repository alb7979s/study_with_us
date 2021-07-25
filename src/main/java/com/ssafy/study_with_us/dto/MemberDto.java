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
    private String name;
    private String nickname;
    private Integer age;
    private String department;
    private LocalDateTime studytime;

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
    public MemberDto(Long id, String email, String password, String name, String nickname, Integer age, String department, LocalDateTime studytime) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.department = department;
        this.studytime = studytime;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age='" + age + '\'' +
                ", department='" + department + '\'' +
                ", studytime='" + studytime + '\'' +
                '}';
    }
}