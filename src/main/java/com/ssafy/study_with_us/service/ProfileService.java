package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.*;
import com.ssafy.study_with_us.dto.ProfileDto;
import com.ssafy.study_with_us.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ProfileService {
    private StudyProfileRepository studyProfileRepository;
    private MemberProfileRepository memberProfileRepository;
    private FileUtil fileUtil;

    public ProfileService(StudyProfileRepository studyProfileRepository, MemberProfileRepository memberProfileRepository, FileUtil fileUtil) {
        this.studyProfileRepository = studyProfileRepository;
        this.memberProfileRepository = memberProfileRepository;
        this.fileUtil = fileUtil;
    }

    public StudyProfile studyProfileCreate(MultipartFile mf) throws IOException {
        File imageFile = fileUtil.setImage(mf);
        return studyProfileRepository.save(StudyProfile.builder()
                .id(null)
                .imageOrgName(mf.getOriginalFilename())
                .image(imageFile.getName())
                .path(imageFile.getParent() + "\\")
                .thumbnail(fileUtil.setThumbnail(mf))
                .build());
    }
    public MemberProfile memberProfileCreate(MultipartFile mf) throws IOException {
        File imageFile = fileUtil.setImage(mf);
        return memberProfileRepository.save(MemberProfile.builder()
                .id(null)
                .imageOrgName(mf.getOriginalFilename())
                .image(imageFile.getName())
                .path(imageFile.getParent() + "\\")
                .thumbnail(fileUtil.setThumbnail(mf))
                .build());
    }
}
