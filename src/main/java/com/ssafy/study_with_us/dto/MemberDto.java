package com.ssafy.study_with_us.dto;

import com.ssafy.study_with_us.domain.entity.Member;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String bday;
    private String gender;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .bday(bday)
                .gender(gender)
                .build();
    }

    @Builder
    public MemberDto(Long id, String email, String password, String name, String phone, String bday, String gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.bday = bday;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", bday='" + bday + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}