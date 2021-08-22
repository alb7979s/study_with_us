package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.*;
import com.ssafy.study_with_us.domain.repository.*;
import com.ssafy.study_with_us.dto.*;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.apache.tomcat.websocket.AuthenticationException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;

@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final ThemeRepository themeRepository;
    private final StudyThemeRefRepository studyThemeRefRepository;
    private final StudyMemberRefRepository studyMemberRefRepository;
    private final ScheduleRepository scheduleRepository;
    private final ProfileRepository profileRepository;
    private final ProfileService profileService;
    private final Long pagingSize;

    public StudyService(StudyRepository studyRepository, MemberRepository memberRepository, ThemeRepository themeRepository, StudyThemeRefRepository studyThemeRefRepository, StudyMemberRefRepository studyMemberRefRepository, ScheduleRepository scheduleRepository, ProfileRepository profileRepository, ProfileService profileService, @Value("${paging.size}") Long pagingSize) {
        this.studyRepository = studyRepository;
        this.memberRepository = memberRepository;
        this.themeRepository = themeRepository;
        this.studyThemeRefRepository = studyThemeRefRepository;
        this.studyMemberRefRepository = studyMemberRefRepository;
        this.scheduleRepository = scheduleRepository;
        this.profileRepository = profileRepository;
        this.profileService = profileService;
        this.pagingSize = pagingSize;
    }

    // 가입, params.memberId null이면 직접 가입 => 토큰에서 정보 얻어옴, null 아니면 초대 => 받은 아이디 정보로 가입
    public StudyMemberDto joinMember(IdReqDto params) throws AuthenticationException {
        Study study = studyRepository.getById(params.getStudyId());
        // 초대시 스터디 장 아니면 예외 처리
        if(params.getMemberId() != null && getMemberId() != study.getStudyLeader()){
            throw new AuthenticationException("스터디 장만 초대 가능합니다.");
        }
        StudyMemberRef studyMemberRef = studyMemberRefRepository.save(StudyMemberRef.builder()
                .member(memberRepository.getById(params.getMemberId() == null ? getMemberId() : params.getMemberId()))
                .study(study)
                .build());
        return StudyMemberDto.builder()
                .id(studyMemberRef.getId())
                .member(studyMemberRef.getMember().entityToDto())
                .study(studyMemberRef.getStudy().entityToDto()).build();
    }

    @Transactional
    public Object withdraw(IdReqDto params) {
        if (params.getMemberId() == null) params = IdReqDto.builder().memberId(getMemberId()).studyId(params.getStudyId()).build();
        return studyMemberRefRepository.withdraw(params);
    }

    /*
    * 1. 스터디 생성
    * 2. set으로 contain 확인 한 후에 없으면 생성
    * 3. 해시태그 목록 set으로 가져옴
    * 4. 만들어진 스터디 id + themes로(해시태그들) 맵핑 테이블에 저장
    */
    @Transactional
    public StudyDto create(FileReqDto params) throws IOException {
        Study study = saveStudyAtFile(params);
        studyMemberRefRepository.save(StudyMemberRef.builder()
                .study(study)
                .member(memberRepository.getById(getMemberId())).build());
        makeThemes(getThemes(params), study);
        return study.entityToDto();
    }

    public Study getStudy(Long studyId){
        return studyRepository.getById(studyId);
    }

    // 여기 insert, delete 모듈화 가능할듯 일단 돌아가게 만들고 후에 수정
    @Transactional(rollbackFor = {IOException.class, AuthenticationException.class})
    public StudyDto update(FileReqDto params) throws IOException, AuthenticationException {
        Study study = saveStudyAtFile(params);
        if(study.getStudyLeader() != getMemberId()) throw new AuthenticationException("스터디 장만 수정 가능합니다.");
        List<String> getThemes = new ArrayList<>();
        for (Theme theme : studyRepository.getThemes(study.getId())) {
            getThemes.add(theme.getThemeName());
        }
        List<String> paramThemes = getThemes(params);

        List<String> insertThemes = new ArrayList<>();
        List<String> deleteThemes = new ArrayList<>();
        for (String paramTheme : paramThemes) {
            if(!getThemes.contains(paramTheme)) insertThemes.add(paramTheme);
        }
        for (String getTheme : getThemes) {
            if(!paramThemes.contains(getTheme)) deleteThemes.add(getTheme);
        }
        makeThemes(insertThemes, study);
        removeThemes(deleteThemes, study);
        return study.entityToDto();
    }


    public StudyDto getDetail(Long studyId){
        Study study = studyRepository.getById(studyId);
        // themes 얻어오기
        List<Theme> getThemes = studyRepository.getThemes(studyId);
        List<String> themes = new ArrayList<>();
        for (Theme getTheme : getThemes) {
            themes.add(getTheme.getThemeName());
        }
        // profile 얻어오기
        StudyProfile getProfile = studyRepository.getProfile(studyId);
        Profile profile = getProfile == null ? null : StudyProfile.builder()
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
                .profile(profile == null ? null : profile.entityToDto())
                .build();
    }

    public Object getStudyList(Integer page){
        List<Study> studies = studyRepository.getPublicStudies(page);
        Long studyCnt = studyRepository.getPublicStudiesCount();
        Long totalPage = studyCnt / pagingSize + (studyCnt % pagingSize == 0 ? 0 : 1);
        Map<String, Object> map = new HashMap<>();
        List<StudyDto> results = new ArrayList<>();
        for (Study study : studies) {
            Profile profile = study.getProfile();
            results.add(StudyDto.builder().id(study.getId()).studyName(study.getStudyName())
                    .studyLeader(study.getStudyLeader()).security(study.getSecurity())
                    .studyIntro(study.getStudyIntro())
                    .themes(study.getThemesString())
                    .profile(profile == null ? null :profile.entityToDto())
                    .build());
        }
        map.put("studies", results);
        map.put("totalPage", totalPage);
        return map;
    }
    private void removeThemes(List<String> deleteThemes, Study study) {
        for (String deleteTheme : deleteThemes) {
            studyRepository.remove(deleteTheme, study.getId());
        }
    }

    private void makeThemes(List<String> getThemes, Study study) {
        // DB에 있는 theme 목록 가져와서 set으로 => 순서 유지 요청 들어와서 list로 변경
        List<Theme> themeList = studyRepository.getThemes();
        List<String> themes = new ArrayList<>();
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

    public Object searchStudyByThemes(List<String> themes, Integer page){
        List<Long> studyIds = studyThemeRefRepository.searchStudyByThemes(themes, page);
        Long studyCnt = studyThemeRefRepository.countStudyByThemes(themes);
        Long totalPage = studyCnt / pagingSize + (studyCnt % pagingSize == 0 ? 0 : 1);
        Map<String, Object> map = new HashMap<>();
        List<StudyDto> results = new ArrayList<>();
        for (Long studyId : studyIds) {
            results.add(getDetail(studyId));
        }
        map.put("studies", results);
        map.put("totalPage", totalPage);
        return map;
    }

    public StudyMemberRefDto connectStudy(StudyMemberRefDto params){
        StudyMemberRef studyMember = studyMemberRefRepository.getStudyMember(getMemberId(), params.getStudyId());
        return studyMemberRefRepository.save(StudyMemberRef.builder().id(studyMember.getId())
                .nickname(params.getNickname())
                .connected(true)
                .member(studyMember.getMember())
                .study(studyMember.getStudy()).recentlyConnectionTime(LocalDateTime.now()).build()).entityToRefDto();
    }

    @Transactional
    public StudyMemberRefDto disConnect(Long studyId){
        StudyMemberRef studyMember = studyMemberRefRepository.getStudyMember(getMemberId(), studyId);
        return studyMemberRefRepository.save(StudyMemberRef.builder()
                .id(studyMember.getId())
                .nickname(studyMember.getNickname())
                .connected(false)
                .recentlyConnectionTime(studyMember.getRecentlyConnectionTime())
                .member(studyMember.getMember())
                .recentlyConnectionTime(studyMember.getRecentlyConnectionTime())
                .study(studyMember.getStudy()).build()).entityToRefDto();
    }

    public List<StudyMemberRefDto> getConnectionList(Long studyId){
        List<StudyMemberRef> connectionList = studyMemberRefRepository.getConnectionList(studyId);
        List<StudyMemberRefDto> results = new ArrayList<>();
        for (StudyMemberRef studyMemberRef : connectionList) {
            results.add(studyMemberRef.entityToRefDto());
        }
        return results;
    }

    public List<StudyMemberDto> getRecentlyStudies(){
        List<StudyMemberRef> recentlyStudies = studyMemberRefRepository.getRecentlyStudies(getMemberId());
        List<StudyMemberDto> results = new ArrayList<>();
        for (StudyMemberRef recentlyStudy : recentlyStudies) {
            results.add(recentlyStudy.entityToDto());
        }
        return results;
    }

    public List<ScheduleDto> getSchedules(Long studyId, LocalDate startDate){
        LocalDate endDate = YearMonth.from(startDate).atEndOfMonth();
        List<Schedule> schedules = scheduleRepository.getScheduleByStudyIdAndScheduleDateBetween(studyId, startDate, endDate);
        ArrayList<ScheduleDto> results = new ArrayList<>();
        for (Schedule schedule : schedules) {
            results.add(schedule.entityToDto());
        }
        return results;
    }
    @Transactional
    public ScheduleDto saveSchedule(ScheduleDto params){
        return scheduleRepository.save(Schedule.builder().id(params.getScheduleId())
                .study(studyRepository.getById(params.getStudyId()))
                .scheduleDate(params.getScheduleDate()).info(params.getInfo()).build()).entityToDto();
    }

    @Transactional
    public void deleteSchedule(Long scheduleId){
        scheduleRepository.delete(scheduleRepository.getById(scheduleId));
    }

    private Long getMemberId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return memberRepository.findByEmail(s).get().getId();
    }
    private Study saveStudy(StudyDto params) {
        ProfileDto profile = params.getProfile();
        return studyRepository.save(Study.builder()
                .id(params.getId())
                .studyName(params.getStudyName())
                .studyIntro(params.getStudyIntro())
                .studyLeader(getMemberId())
                .security(params.getSecurity())
                .profile(profile == null ? null : StudyProfile.builder().id(profile.getId()).imageOrgName(profile.getImageOrgName()).image(profile.getImage())
                        .path(profile.getPath()).thumbnail(profile.getThumbnail()).build())
                .build());
    }

    private List<String> getThemes(FileReqDto params){
        JSONObject jObject = new JSONObject(params.getJsonData());
        List<String> themes = new ArrayList<>();

        if (jObject.has("themes")){
            for (Object theme : jObject.getJSONArray("themes")) {
                themes.add((String) theme);
            }
        }
        return themes;
    }
    private Study saveStudyAtFile(FileReqDto params) throws IOException {
        JSONObject jObject = new JSONObject(params.getJsonData());
        Long deletedProfileId = null;
        Study study = null;
        if(jObject.has("studyId")) {
            study = studyRepository.getById(jObject.getLong("studyId"));
            Profile prevProfile = study.getProfile();
            if(prevProfile != null) {
                deletedProfileId = prevProfile.getId();
            }
        }
        Profile profile = profileService.studyProfileCreate(params.getFiles().size() > 0 ? params.getFiles().get(0) : null);
        if(deletedProfileId != null) {
            profileRepository.deleteById(deletedProfileId);
        }
        // study
        return studyRepository.save(Study.builder()
                .id(jObject.has("studyId") ? jObject.getLong("studyId") : null)
                .studyName(jObject.has("studyName") ? jObject.getString("studyName") : null)
                .studyIntro(jObject.has("studyIntro") ? jObject.getString("studyIntro") : null)
                .studyLeader(study == null ? getMemberId(): study.getStudyLeader())
                .security(jObject.has("security") ? jObject.getString("security") : null)
                .profile(profile == null ? null : (StudyProfile) profile)
                .build());
    }
}
