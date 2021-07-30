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

import java.util.*;

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

    // 여러개 들어오는거 처리할 수 있게끔?
    public Object joinMembers(){
        return null;
    }
    public Object joinMember(StudyDto params){
        // member 토큰으로 얻어와서
        // memberStudyRef에 추가
        return null;
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
                .profile((StudyProfile) params.getProfile())
                .build());
        makeThemes(params.getThemes(), study);
        return study;
    }

    // 여기 insert, delete 모듈화 가능할듯 일단 돌아가게 만들고 후에 수정
    @Transactional
    public Object update(StudyDto params){
        Study study = studyRepository.getById(params.getId());
        studyRepository.update(StudyDto.builder()
                .id(params.getId())
                .studyName(params.getStudyName() == null ? study.getStudyName() : params.getStudyName())
                .studyIntro(params.getStudyIntro() == null ? study.getStudyIntro() : params.getStudyIntro())
                .build());

        Set<String> getThemes = new HashSet<>();
        for (Theme theme : studyRepository.getThemes(study.getId())) {
            getThemes.add(theme.getThemeName());
        }
        Set<String> paramThemes = params.getThemes();

        Set<String> insertThemes = new HashSet<>();
        Set<String> deleteThemes = new HashSet<>();
        for (String paramTheme : paramThemes) {
            if(!getThemes.contains(paramTheme)) insertThemes.add(paramTheme);
        }
        for (String getTheme : getThemes) {
            if(!paramThemes.contains(getTheme)) deleteThemes.add(getTheme);
        }
        makeThemes(insertThemes, study);
        removeThemes(deleteThemes, study);
        return studyRepository.update(params);
    }


    public Object read(Long studyId){
        Study study = studyRepository.getById(studyId);
        // themes 얻어오기
        List<Theme> getThemes = studyRepository.getThemes(studyId);
        Set<String> themes = new HashSet<>();
        for (Theme getTheme : getThemes) {
            themes.add(getTheme.getThemeName());
        }
        // profile 얻어오기
        StudyProfile getProfile = studyRepository.getProfile(studyId);
        Profile profile = StudyProfile.builder()
                .id(getProfile.getId())
                .imageOrgName(getProfile.getImageOrgName())
                .path(getProfile.getPath())
                .image(getProfile.getImage())
                .thumbnail(getProfile.getThumbnail()).build();
        return StudyDto.builder()
                .id(study.getId())
                .studyName(study.getStudyName())
                .studyIntro(study.getStudyIntro())
                .studyLeader(study.getStudyLeader())
                .security(study.getSecurity())
                .themes(themes)
                .profile(profile)
                .build();
    }
    private void removeThemes(Set<String> deleteThemes, Study study) {
        for (String deleteTheme : deleteThemes) {
            studyRepository.remove(deleteTheme, study.getId());
        }
    }

    private void makeThemes(Set<String> getThemes, Study study) {
        // DB에 있는 theme 목록 가져와서 set으로
        List<Theme> themeList = studyRepository.getThemes();
        Set<String> themes = new HashSet<>();
        for (Theme theme : themeList) {
            themes.add(theme.getThemeName());
        }
        // 안들어있으면 theme 테이블에 생성
        for (String theme : getThemes) {
            if (!themes.contains(theme)) themeRepository.save(Theme.builder().themeName(theme).build());
        }
        // 매핑 테이블에 저장
        for (String theme : getThemes) {
            studyThemeRefRepository.save(StudyThemeRef.builder().study(study).theme(Theme.builder().themeName(theme).build()).build());
        }
    }

    private Long getMemberId(Optional<String> currentUsername) {
        String s = currentUsername.get();
        return memberRepository.findByEmail(s).get().getId();
    }
}
