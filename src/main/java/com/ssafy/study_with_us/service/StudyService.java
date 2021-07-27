package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.*;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.domain.repository.StudyRepository;
import com.ssafy.study_with_us.domain.repository.StudyThemeRefRepository;
import com.ssafy.study_with_us.domain.repository.ThemeRepository;
import com.ssafy.study_with_us.dto.ProfileDto;
import com.ssafy.study_with_us.dto.StudyDto;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudyService {
    private StudyRepository studyRepository;
    private MemberRepository memberRepository;
    private ThemeRepository themeRepository;
    private StudyThemeRefRepository studyThemeRefRepository;

    public StudyService(StudyRepository studyRepository, MemberRepository memberRepository, ThemeRepository themeRepository, StudyThemeRefRepository studyThemeRefRepository) {
        this.studyRepository = studyRepository;
        this.memberRepository = memberRepository;
        this.themeRepository = themeRepository;
        this.studyThemeRefRepository = studyThemeRefRepository;
    }

    /*
    * 1. 스터디 생성
    * 2. set으로 contain 확인 한 후에 없으면 생성
    * 3. 해시태그 목록 set으로 가져옴
    * 4. 만들어진 스터디 id + themes로(해시태그들) 맵핑 테이블에 저장
    */
    public Object create(StudyDto params){
        // 1. 스터디 생성 (여기 profile_id 어떻게 저장할지 생각해보기)
        Study study = studyRepository.save(Study.builder()
                .id(null)
                .studyName(params.getStudyName())
                .studyIntro(params.getStudyIntro())
                .studyLeader(getMemberId(SecurityUtil.getCurrentUsername()))
                .security(params.getSecurity())
                .profile(StudyProfile.builder().id(2l).build())    // 임시로 만듦, 수정해줘야함
                .build());
        makeThemes(params, study);
        return study;
    }

    @Transactional
    public Object update(StudyDto params){
        Study study = studyRepository.getById(params.getId());
        studyRepository.update(StudyDto.builder()
                .id(params.getId())
                .studyName(params.getStudyName() == null ? study.getStudyName() : params.getStudyName())
                .studyIntro(params.getStudyIntro() == null ? study.getStudyIntro() : params.getStudyIntro())
                .build());
        makeThemes(params, study);
        return studyRepository.update(params);
    }
    public Object read(Long id){
        Study study = studyRepository.getById(id);
        // themes 얻어오기
        List<StudyThemeRef> getThemes = studyRepository.getThemes(id);
        Set<String> themes = new HashSet<>();
        for (StudyThemeRef theme : getThemes) {
            themes.add(theme.getTheme().getThemeName());
        }
        // profile 얻어오기
        Study profile1 = studyRepository.getProfile(id);
        System.out.println("profile1 = " + profile1);
//        ProfileDto profile = ProfileDto.builder()
//                .id(getProfile.getId())
//                .path(getProfile.getPath())
//                .image(getProfile.getImage())
//                .thumbnail(getProfile.getThumbnail()).build();
        return StudyDto.builder()
                .id(study.getId())
                .studyName(study.getStudyName())
                .studyIntro(study.getStudyIntro())
                .studyLeader(study.getStudyLeader())
                .security(study.getSecurity())
                .themes(themes)
//                .profile(profile)
                .build();
    }

    private void makeThemes(StudyDto params, Study study) {
        // DB에 있는 theme 목록 가져와서 set으로
        List<Theme> themeList = studyRepository.getThemes();
        Set<String> themes = new HashSet<>();
        for (Theme theme : themeList) {
            themes.add(theme.getThemeName());
        }
        // 안들어있으면 theme 테이블에 생성
        for (String theme : params.getThemes()) {
            if (!themes.contains(theme)) themeRepository.save(Theme.builder().themeName(theme).build());
        }
        // 매핑 테이블에 저장
        for (String theme : params.getThemes()) {
            studyThemeRefRepository.save(StudyThemeRef.builder().study(study).theme(Theme.builder().themeName(theme).build()).build());
        }
    }

    private Long getMemberId(Optional<String> currentUsername) {
        String s = currentUsername.get();
        return memberRepository.findByEmail(s).get().getId();
    }
}
