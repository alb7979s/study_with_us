package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.MemberProfile;
import com.ssafy.study_with_us.domain.entity.Profile;
import com.ssafy.study_with_us.domain.entity.StudyProfile;
import com.ssafy.study_with_us.domain.repository.*;
import com.ssafy.study_with_us.util.FileUtil;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ProfileService {
    private final StudyProfileRepository studyProfileRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;
    private final FileUtil fileUtil;

    public ProfileService(StudyProfileRepository studyProfileRepository, MemberProfileRepository memberProfileRepository, MemberRepository memberRepository, StudyRepository studyRepository, FileUtil fileUtil) {
        this.studyProfileRepository = studyProfileRepository;
        this.memberProfileRepository = memberProfileRepository;
        this.memberRepository = memberRepository;
        this.studyRepository = studyRepository;
        this.fileUtil = fileUtil;
    }

    @Transactional
    public StudyProfile studyProfileCreate(MultipartFile mf) throws IOException {
        if(mf == null || mf.getSize() == 0) return null;
        File imageFile = fileUtil.setImage(mf);
        return studyProfileRepository.save(StudyProfile.builder()
                .id(null)
                .imageOrgName(mf.getOriginalFilename())
                .image(imageFile.getName())
                .path(imageFile.getParent() + "/")
                .thumbnail(fileUtil.setThumbnail(imageFile))
                .build());
    }
    @Transactional
    public MemberProfile memberProfileCreate(MultipartFile mf) throws IOException {
        if(mf == null || mf.getSize() == 0) return null;
        File imageFile = fileUtil.setImage(mf);
        return memberProfileRepository.save(MemberProfile.builder()
                .id(null)
                .imageOrgName(mf.getOriginalFilename())
                .image(imageFile.getName())
                .path(imageFile.getParent() + "/")
                .thumbnail(fileUtil.setThumbnail(imageFile))
                .build());
    }

    public String getProfile(Long studyId, Long memberId){
        Profile profile = null;
        if(studyId == null){
            profile = memberRepository.getById(memberId == null ? getMemberId() : memberId).getProfile();
        } else {
            profile = studyRepository.getById(studyId).getProfile();
        }
        return profile == null ? null : (profile.getPath() + profile.getImage());
    }

    private Long getMemberId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return memberRepository.findByEmail(s).get().getId();
    }
}
