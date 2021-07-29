package com.ssafy.study_with_us.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String username;

    @Column
    private Integer age;

    @Column(length = 30)
    private String department;

    @Column
    private LocalDateTime studytime;

    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

//  방향 관계 확실치 않아서 일단 생성자에 추가 안했어요!
    @OneToOne
    @JoinColumn(name = "profile_id")
    private MemberProfile profile;

    @Builder
    public Member(Long id, String email, String password, String username, Integer age, String department, MemberProfile profile, LocalDateTime studytime) {
        this(id, email, password, username, age, department, profile, studytime, Collections.singleton(Authority.builder().authorityName("ROLE_USER").build()));
//        this(id, email, password, name, nickname, age, group, studytime, Collections.singleton(MemberAuthorityRef.builder().authority(Authority.builder().authorityName("ROLE_USER").build()));
    }

    @Builder
    public Member(Long id, String email, String password, String username, Integer age, String department, MemberProfile profile, LocalDateTime studytime, Set<Authority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
        this.department = department;
        this.profile = profile;
        this.studytime = studytime;
        this.authorities = authorities;
    }
    public Member() {
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                '}';
    }

}