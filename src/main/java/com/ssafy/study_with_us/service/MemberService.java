package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.domain.entity.MemberProfile;
import com.ssafy.study_with_us.domain.entity.Profile;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.domain.repository.ProfileRepository;
import com.ssafy.study_with_us.dto.MemberDto;
import com.ssafy.study_with_us.dto.ProfileDto;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, ProfileRepository profileRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public Member joinMember(MemberDto params) {
        return memberRepository.save(Member.builder()
                .email(params.getEmail())
                .password(passwordEncoder.encode(params.getPassword()))
                .username(params.getUsername())
                .age(params.getAge())
                .department(params.getDepartment())
                .profile((MemberProfile) params.getProfile())
                .build());
    }
//    @Transactional
//    public MemberDto readMember(MemberDto param) {
//        Member member = memberRepository.findByEmail(param.getEmail()).get();
//        MemberProfile profile = member.getProfile();
//        ProfileDto profileDto = new ProfileDto(profile.getId(), profile.getImage(), profile.getThumbnail(), profile.getPath());
//        return new MemberDto(member.getId(), member.getEmail(), member.getPassword(), member.getUsername(), member.getAge(), member.getDepartment(), member.getStudytime(), profileDto);
//    }
//
//    @Transactional
//    public Member updateMember(MemberDto param) {
//        Member member = memberRepository.findByEmail(param.getEmail()).get();
//        // 이거 원래 이렇게 다 체크해줘야함??
//        if(param.getId() == null) param.setId(member.getId());
//        if(param.getPassword() == null) param.setPassword(member.getPassword());
//        else{
//            param.setPassword(passwordEncoder.encode(param.getPassword()));
//        }
//        if(param.getUsername() == null) param.setUsername(member.getUsername());
//        if(param.getAge() == null) param.setAge(member.getAge());
//        if(param.getDepartment() == null) param.setDepartment(member.getDepartment());
//        ProfileDto profileDto = param.getProfile();
//        MemberProfile profile = (MemberProfile) profileRepository.findById(member.getProfile().getId()).get();
//        profile.setImage(profileDto.getImage());
//        profile.setThumbnail(profileDto.getThumbnail());
//        profile.setPath(profileDto.getPath());
//        profileRepository.save(profile);
//        return memberRepository.save(new Member(param.getId(), param.getEmail(), param.getPassword(), param.getUsername(), param.getAge(), param.getDepartment(), param.getStudytime(), profile));
//    }
//    @Transactional
//    public void deleteMember(String email) {
//        memberRepository.delete(this.memberRepository.findByEmail(email).get());
//    }
//
//    public Member dtoToEntity(MemberDto memberDto){
//        return new Member(memberDto.getId(), memberDto.getEmail(), memberDto.getPassword(), memberDto.getUsername(), memberDto.getAge(), memberDto.getDepartment(), memberDto.getStudytime());
//    }
//
    public Member pwdSearch(MemberDto memberDto, String key){
        Member member = memberRepository.findByEmail(memberDto.getEmail()).get();
        member.setPassword(passwordEncoder.encode(key));
        memberRepository.save(member);
        return member;
    }
}
