package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.dto.MemberDto;
import com.ssafy.study_with_us.dto.TokenDto;
import com.ssafy.study_with_us.jwt.JwtFilter;
import com.ssafy.study_with_us.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, MemberRepository memberRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/login")
    public Object authorize(@Valid @RequestBody MemberDto member) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword());

        // 여기서 CustomUserDetailsService - loadUserByUserName 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        Map<String, Object> map = new HashMap<>();
        map.put("Token", new TokenDto(jwt));
        map.put("member", memberRepository.findByEmail(member.getEmail()));
        return map;
    }
}
