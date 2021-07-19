package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthorityService {
    private final MemberRepository memberRepository;

    public AuthorityService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Optional<Member> getMemberWithAuthorities(String email){
        return memberRepository.findOneWithAuthoritiesByEmail(email);
    }

    @Transactional
    public Optional<Member> getMyMemberWithAuthorities(){
        return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByEmail);
    }
}
