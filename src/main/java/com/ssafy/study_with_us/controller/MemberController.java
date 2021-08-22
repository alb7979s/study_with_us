package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.domain.entity.MemberProfile;
import com.ssafy.study_with_us.domain.entity.Profile;
import com.ssafy.study_with_us.dto.*;
import com.ssafy.study_with_us.error.ErrorResponse;
import com.ssafy.study_with_us.error.exception.ErrorCode;
import com.ssafy.study_with_us.error.exception.InvalidValueException;
import com.ssafy.study_with_us.service.AuthorityService;
import com.ssafy.study_with_us.service.MailService;
import com.ssafy.study_with_us.service.MemberService;
import com.ssafy.study_with_us.service.ProfileService;
import com.ssafy.study_with_us.response.ApiResult;
import com.ssafy.study_with_us.response.ResponseMessage;
import com.ssafy.study_with_us.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;
import org.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.Format;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@Slf4j
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
        Profile profile = profileService.memberProfileCreate(params.getFiles().size() > 0 ? params.getFiles().get(0) : null);
        // member
        JSONObject jObject = new JSONObject(params.getJsonData());
        if(!jObject.has("email")) throw new InvalidValueException("이메일이 비었습니다.");
        if(!jObject.has("password")) throw new InvalidValueException("패스워드가 비었습니다.");
        if(!jObject.has("username")) throw new InvalidValueException("이름이 비었습니다.");
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
    public Object pwdSearch(@RequestBody MemberDto param){
        String key = mailService.sendAuthMail(param.getEmail());
        Member member = memberService.pwdSearch(param, key);
        String msg = member.getEmail() + "로 메일이 전송되었습니다. 메일을 통해 인증해주세요";
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.PWD_MAIL_SUCCESS).dataType("String").data(msg).build();
    }

    @GetMapping("/studycheck/{studyId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object isStudy(@PathVariable("studyId") Long studyId){
        return memberService.isStudy(studyId);
    }

    @PostMapping("/time")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object addTime(@RequestBody @Valid StudyTimeDto params){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.ADDED_STUDY_TIME).dataType("member_study").data(memberService.addTime(params)).build();
    }
    @GetMapping("/time")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object getTimeList(){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_STUDY_TIME_LIST).dataType("study_time_list").data(memberService.getTimeList()).build();
    }

    // email 중복
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(Exception e) {
        log.error("이메일(pk) 중복", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.EMAIL_DUPLICATION);
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(InvalidValueException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidValueException(Exception e) {
        log.error("올바르지 않은 request 값", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }
    
    @ExceptionHandler(UnsupportedFormatException.class)
    protected ResponseEntity<ErrorResponse> handleUnsupportedFormatException(Exception e) {
        log.error("이미지 형식 에러", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.FILE_FORMAT_ERROR);
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ErrorResponse> handleNoSuchElementException(Exception e) {
        log.error("해당 이메일을 찾을 수 없습니다.", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.EMAIL_NOT_FOUNDED);
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }

}