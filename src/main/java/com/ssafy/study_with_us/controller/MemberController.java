package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.dto.MemberDto;
import com.ssafy.study_with_us.service.AuthorityService;
import com.ssafy.study_with_us.service.MailService;
import com.ssafy.study_with_us.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthorityService authorityService;
    private final MailService mailService;

    public MemberController(MemberService memberService, AuthorityService authorityService, MailService mailService) {
        this.memberService = memberService;
        this.authorityService = authorityService;
        this.mailService = mailService;
    }

    //  회원가입
    @PostMapping("/join")
    public Object join(@RequestBody MemberDto memberDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("member", memberService.joinMember(memberDto));
        return map;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object update(@RequestBody MemberDto param){
        Map<String, Object> map = new HashMap<>();
        map.put("member", memberService.updateMember(param));
        return map;
    }

    @GetMapping("/read")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object readMember(){
        Map<String, Object> map = new HashMap<>();
        map.put("member", ResponseEntity.ok(authorityService.getMyMemberWithAuthorities()));
        return map;
    }

    @GetMapping("/read/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Object readMember(@PathVariable String email){
        Map<String, Object> map = new HashMap<>();
        map.put("member", ResponseEntity.ok(authorityService.getMemberWithAuthorities(email)));
        return map;
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void deleteMember(@PathVariable String email){
        memberService.deleteMember(email);
    }

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