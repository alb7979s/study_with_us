package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.*;
import com.ssafy.study_with_us.domain.repository.MemberProfileRepository;
import com.ssafy.study_with_us.domain.repository.StudyProfileRepository;
import com.ssafy.study_with_us.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ProfileService {
    private final StudyProfileRepository studyProfileRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final FileUtil fileUtil;

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
    @Transactional
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
