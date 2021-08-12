package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.domain.entity.MemberProfile;
import com.ssafy.study_with_us.domain.entity.Profile;
import com.ssafy.study_with_us.domain.entity.StudyTime;
import com.ssafy.study_with_us.dto.*;
import com.ssafy.study_with_us.service.AuthorityService;
import com.ssafy.study_with_us.service.MailService;
import com.ssafy.study_with_us.service.MemberService;
import com.ssafy.study_with_us.service.ProfileService;
import com.ssafy.study_with_us.util.response.ApiResult;
import com.ssafy.study_with_us.util.response.ResponseMessage;
import com.ssafy.study_with_us.util.response.StatusCode;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthorityService authorityService;
    private final MailService mailService;
    private final ProfileService profileService;

    public MemberController(MemberService memberService, AuthorityService authorityService, MailService mailService, ProfileService profileService) {
        this.memberService = memberService;
        this.authorityService = authorityService;
        this.mailService = mailService;
        this.profileService = profileService;
    }

    //  회원가입
    @PostMapping("/join")
    public Object join(FileReqDto params) throws IOException {
        // 파일 정보 있으면 받은 정보로 생성
        Profile profile = profileService.memberProfileCreate(params.getFiles().get(0));
        // member
        JSONObject jObject = new JSONObject(params.getJsonData());

        Member result = memberService.joinMember(
                MemberDto.builder()
                        .email(jObject.has("email") ? jObject.getString("email") : null)
                        .password(jObject.has("password") ? jObject.getString("password") : null)
                        .username(jObject.has("username") ? jObject.getString("username") : null)
                        .age(jObject.has("age") ? jObject.getInt("age") : null)
                        .department(jObject.has("department") ? jObject.getString("department") : null)
                        .profile(profile)
                        .build()
        );
        return ApiResult.builder().status(StatusCode.OK)
                .message(ResponseMessage.CREATED_MEMBER)
                .dataType("member").data(result).build();
    }

//    @PostMapping("/update")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public Object update(@RequestBody MemberDto param){
//        Map<String, Object> map = new HashMap<>();
//        map.put("member", memberService.updateMember(param));
//        return map;
//    }
//
//    @GetMapping("/read")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public Object readMember(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("member", ResponseEntity.ok(authorityService.getMyMemberWithAuthorities()));
//        return map;
//    }

    @GetMapping("/read/{email}")
    public Object readMember(@PathVariable String email){
        MemberResDto memberResDto;
        try{
            Member member = authorityService.getMemberWithAuthorities(email).get();
            MemberProfile memberProfile = member.getProfile();
//            ProfileDto profileDto = new ProfileDto(memberProfile.getId(), memberProfile.getImage(), memberProfile.getThumbnail(), memberProfile.getPath());
//            MemberDto memberDto = new MemberDto(member.getId(), member.getEmail(), member.getPassword(), member.getUsername(), member.getAge(), member.getDepartment(), member.getStudytime(), profileDto);
            ProfileDto profileDto = memberProfile == null ? null : ProfileDto.builder().id(memberProfile.getId()).imageOrgName(memberProfile.getImageOrgName()).image(memberProfile.getImage()).thumbnail(memberProfile.getThumbnail()).path(memberProfile.getPath()).build();
            memberResDto = MemberResDto.builder().id(member.getId()).age(member.getAge()).department(member.getDepartment()).email(member.getEmail()).password(member.getPassword()).username(member.getUsername()).profile(profileDto).build();
        }catch (Exception e){
            return ApiResult.builder().status(StatusCode.UNAUTHORIZED).message(ResponseMessage.NOT_FOUND_MEMBER).dataType("String").data(email).build();
        }
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.READ_MEMBER).dataType("member").data(memberResDto).build();
    }
//
//    @DeleteMapping("/{email}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public void deleteMember(@PathVariable String email){
//        memberService.deleteMember(email);
//    }
//
    @PostMapping("/pwdSearch")
    public Object pwdSearch(@RequestBody  MemberDto param){
        String key = mailService.sendAuthMail(param.getEmail());
        Member member = memberService.pwdSearch(param, key);
        String msg = member.getEmail() + "로 메일이 전송되었습니다. 메일을 통해 인증해주세요";
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.PWD_MAIL_SUCCESS).dataType("String").data(msg).build();
    }

    @GetMapping("/studycheck/{studyId}")
    public Object isStudy(@PathVariable("studyId") Long studyId){
        return memberService.isStudy(studyId);
    }

    @PostMapping("/time")
    public Object addTime(@RequestBody StudyTimeDto params){
//      후에 params.getStudyTime() == null 이면 예외처리 해줘야함
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.ADDED_STUDY_TIME).dataType("member_study").data(memberService.addTime(params)).build();
    }
    @GetMapping("/time")
    public Object getTimeList(){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_STUDY_TIME_LIST).dataType("study_time_list").data(memberService.getTimeList()).build();
    }
}