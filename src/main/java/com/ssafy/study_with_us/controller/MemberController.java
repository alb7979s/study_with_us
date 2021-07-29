package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.domain.entity.MemberProfile;
import com.ssafy.study_with_us.domain.entity.Profile;
import com.ssafy.study_with_us.dto.FileDto;
import com.ssafy.study_with_us.dto.MemberDto;
import com.ssafy.study_with_us.dto.MemberResDto;
import com.ssafy.study_with_us.dto.ProfileDto;
import com.ssafy.study_with_us.service.AuthorityService;
import com.ssafy.study_with_us.service.MailService;
import com.ssafy.study_with_us.service.MemberService;
import com.ssafy.study_with_us.service.ProfileService;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public Object join(FileDto params) throws IOException {
        Profile profile = null;
        // 파일 정보 있으면 받은 정보로 생성
        if (params.getFiles() != null) {
            profile = profileService.memberProfileCreate(params.getFiles().get(0));
        }
        // member
        JSONObject jObject = new JSONObject(params.getJsonData());

        Map<String, Object> map = new HashMap<>();
        map.put("member", memberService.joinMember(
                MemberDto.builder()
                        .email(jObject.has("email") ? jObject.getString("email") : null)
                        .password(jObject.has("password") ? jObject.getString("password") : null)
                        .username(jObject.has("username") ? jObject.getString("username") : null)
                        .age(jObject.has("age") ? jObject.getInt("age") : null)
                        .department(jObject.has("department") ? jObject.getString("department") : null)
                        .profile(profile)
                        .build()
        ));
        return map;
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
        Map<String, Object> map = new HashMap<>();
        try{
            Member member = authorityService.getMemberWithAuthorities(email).get();
            MemberProfile memberProfile = member.getProfile();
//            ProfileDto profileDto = new ProfileDto(memberProfile.getId(), memberProfile.getImage(), memberProfile.getThumbnail(), memberProfile.getPath());
//            MemberDto memberDto = new MemberDto(member.getId(), member.getEmail(), member.getPassword(), member.getUsername(), member.getAge(), member.getDepartment(), member.getStudytime(), profileDto);
            ProfileDto profileDto = ProfileDto.builder().id(memberProfile.getId()).imageOrgName(memberProfile.getImageOrgName()).image(memberProfile.getImage()).thumbnail(memberProfile.getThumbnail()).path(memberProfile.getPath()).build();
            MemberResDto memberResDto = MemberResDto.builder().id(member.getId()).age(member.getAge()).department(member.getDepartment()).email(member.getEmail()).password(member.getPassword()).username(member.getUsername()).studytime(member.getStudytime()).profile(profileDto).build();
            map.put("member", memberResDto);
        }catch (Exception e){
            map.put("msg", "해당 아이디를 가진 사용자가 없습니다.");
        }
        return map;
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
        Map<String, Object> map = new HashMap<>();
        String key = mailService.sendAuthMail(param.getEmail());
        Member member = memberService.pwdSearch(param, key);
        String msg = member.getEmail() + "로 메일이 전송되었습니다. 메일을 통해 인증해주세요";
        map.put("msg" , msg);
        return map;
    }

}