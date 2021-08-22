package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.error.ErrorResponse;
import com.ssafy.study_with_us.error.exception.ErrorCode;
import com.ssafy.study_with_us.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // profile 없을 때 예외처리
    @GetMapping("/study")
    public ResponseEntity<Resource> viewProfileImg(@RequestParam Long studyId) {
        String profile = profileService.getProfile(studyId, null);
        if(profile == null) throw new NoSuchElementException("프로필이 존재하지 않습니다.");
        Path path = new File(profile).toPath();
        return getResponseEntity(path);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Resource> viewMemberImg(@PathVariable("memberId") Long memberId) {
        String profile = profileService.getProfile(null, memberId);
        if(profile == null) throw new NoSuchElementException("프로필이 존재하지 않습니다.");
        Path path = new File(profile).toPath();
        return getResponseEntity(path);
    }

    private ResponseEntity<Resource> getResponseEntity(Path path) {
        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE))
                .body(resource);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ErrorResponse> handleNoSuchElementException(Exception e) {
        log.error("프로필이 존재하지 않습니다.", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.PROFILE_NOT_FOUNDED);
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }
    @ExceptionHandler(FileNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleFileNotFoundException(Exception e) {
        log.error("파일을 찾을 수 없습니다.", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.FILE_NOT_FOUNDED);
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }
}
