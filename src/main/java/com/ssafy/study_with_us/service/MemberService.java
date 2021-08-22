package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.domain.entity.MemberProfile;
import com.ssafy.study_with_us.domain.entity.StudyMemberRef;
import com.ssafy.study_with_us.domain.entity.StudyTime;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.domain.repository.ProfileRepository;
import com.ssafy.study_with_us.domain.repository.StudyMemberRefRepository;
import com.ssafy.study_with_us.domain.repository.StudyTimeRepository;
import com.ssafy.study_with_us.dto.MemberDto;
import com.ssafy.study_with_us.dto.StudyTimeDto;
import com.ssafy.study_with_us.error.exception.InvalidValueException;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private final StudyMemberRefRepository studyMemberRefRepository;
    private final StudyTimeRepository studyTimeRepository;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, ProfileRepository profileRepository, StudyMemberRefRepository studyMemberRefRepository, StudyTimeRepository studyTimeRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
        this.studyMemberRefRepository = studyMemberRefRepository;
        this.studyTimeRepository = studyTimeRepository;
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
        return memberRepository.save(Member.builder().id(member.getId())
                .email(member.getEmail())
                .password(passwordEncoder.encode(key))
                .username(member.getUsername())
                .age(member.getAge())
                .department(member.getDepartment())
                .profile(member.getProfile()).build());
    }

    public Boolean isStudy(Long studyId){
        StudyMemberRef studyMember = studyMemberRefRepository.getStudyMember(getMemberId(), studyId);
        return studyMember == null? false : true;
    }

    public StudyTimeDto addTime(StudyTimeDto params){
        // 여기서 studyTime null이면 만들어주고 아니면 받아온 시간 더해줌
        StudyTime studyTime = studyTimeRepository.getStudyTimeByMember_IdAndStudyDate(getMemberId(), LocalDate.now());
        StudyTime result = null;
        if(studyTime == null) {
            result = studyTimeRepository.save(StudyTime.builder()
                    .studyDate(LocalDate.now()).studyTime(params.getStudyTime())
                    .member(memberRepository.getById(getMemberId())).build());
        } else {
            result = studyTimeRepository.save(StudyTime.builder().id(studyTime.getId())
                    .studyDate(LocalDate.now())
                    .studyTime(params.getStudyTime() + studyTime.getStudyTime())
                    .member(studyTime.getMember()).build());
        }
        return result.entityToDto();
    }
    public List<StudyTimeDto> getTimeList(){
        List<StudyTime> studyTimeList = studyTimeRepository.getByMemberId(getMemberId());
        List<StudyTimeDto> results = new ArrayList<>();
        for (StudyTime studyTime : studyTimeList) {
            results.add(studyTime.entityToDto());
        }
        return results;
    }
    private Long getMemberId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return memberRepository.findByEmail(s).get().getId();
    }
}
