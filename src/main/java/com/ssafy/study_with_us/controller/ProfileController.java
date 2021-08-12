package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.entity.Profile;
import com.ssafy.study_with_us.service.ProfileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // profile 없을 때 예외처리
    @GetMapping("/study")
    public ResponseEntity<Resource> viewProfileImg(@RequestParam Long studyId) throws IOException {
        String profile = profileService.getProfile(studyId, null);
        if(profile == null) return ResponseEntity.noContent().build();
        Path path = new File(profile).toPath();
        return getResponseEntity(path);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Resource> viewMemberImg(@PathVariable("memberId") Long memberId) throws IOException {
        String profile = profileService.getProfile(null, memberId);
        if(profile == null) return ResponseEntity.noContent().build();
        Path path = new File(profile).toPath();
        return getResponseEntity(path);
    }

    private ResponseEntity<Resource> getResponseEntity(Path path) throws IOException {
        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                .body(resource);
    }
}
