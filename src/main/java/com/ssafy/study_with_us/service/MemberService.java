package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.dto.MemberDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Member joinMember(MemberDto memberDto) {
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        return memberRepository.save(dtoToEntity(memberDto));
    }
    @Transactional
    public MemberDto readMember(MemberDto param) {
        Member member = memberRepository.findByEmail(param.getEmail()).get();
        return new MemberDto(member.getId(), member.getEmail(), member.getPassword(), member.getName(), member.getNickname(), member.getAge(), member.getDepartment(), member.getStudytime());
    }

    @Transactional
    public Member updateMember(MemberDto param) {
        Member member = memberRepository.findByEmail(param.getEmail()).get();
        // 이거 원래 이렇게 다 체크해줘야함??
        if(param.getId() == null) param.setId(member.getId());
        if(param.getPassword() == null) param.setPassword(member.getPassword());
        else{
            param.setPassword(passwordEncoder.encode(param.getPassword()));
        }
        if(param.getName() == null) param.setName(member.getName());
        if(param.getNickname() == null) param.setNickname(member.getNickname());
        if(param.getAge() == null) param.setAge(member.getAge());
        if(param.getDepartment() == null) param.setDepartment(member.getDepartment());
        return memberRepository.save(new Member(param.getId(), param.getEmail(), param.getPassword(), param.getName(), param.getNickname(), param.getAge(), param.getDepartment(), param.getStudytime()));
    }
    @Transactional
    public void deleteMember(String email) {
        memberRepository.delete(this.memberRepository.findByEmail(email).get());
    }

    public Member dtoToEntity(MemberDto memberDto){
        return new Member(memberDto.getId(), memberDto.getEmail(), memberDto.getPassword(), memberDto.getName(), memberDto.getNickname(), memberDto.getAge(), memberDto.getDepartment(), memberDto.getStudytime());
    }
}
