//package com.ssafy.study_with_us.domain.entity;
//
//import javax.persistence.*;
//
//import static javax.persistence.FetchType.*;
//
//@Entity
//@Table(name = "member_authority")
//public class MemberAuthority {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "authority_name")
//    private Authority authority;
//}
