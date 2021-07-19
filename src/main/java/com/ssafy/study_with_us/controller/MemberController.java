package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.MemberDto;
import com.ssafy.study_with_us.service.AuthorityService;
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

    public MemberController(MemberService memberService, AuthorityService authorityService) {
        this.memberService = memberService;
        this.authorityService = authorityService;
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
}