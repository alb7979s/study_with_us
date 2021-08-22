package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.Blacklist;
import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.domain.entity.Study;
import com.ssafy.study_with_us.domain.entity.StudyMemberRef;
import com.ssafy.study_with_us.domain.repository.BlacklistRepository;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.domain.repository.StudyMemberRefRepository;
import com.ssafy.study_with_us.domain.repository.StudyRepository;
import com.ssafy.study_with_us.dto.IdReqDto;
import com.ssafy.study_with_us.dto.StudyMemberDto;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlacklistService {
    private final BlacklistRepository blacklistRepository;
    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final StudyMemberRefRepository studyMemberRefRepository;

    public BlacklistService(BlacklistRepository blacklistRepository, StudyRepository studyRepository, MemberRepository memberRepository, StudyMemberRefRepository studyMemberRefRepository) {
        this.blacklistRepository = blacklistRepository;
        this.studyRepository = studyRepository;
        this.memberRepository = memberRepository;
        this.studyMemberRefRepository = studyMemberRefRepository;
    }

    @Transactional
    public Object addBlacklist(IdReqDto params) throws AuthenticationException {
        authCheck(params);
        // 스터디 멤버 삭제
        studyMemberRefRepository.withdraw(params);
        // 블랙리스트 추가
        Blacklist blacklist = blacklistRepository.save(Blacklist.builder()
                .member(memberRepository.getById(params.getMemberId()))
                .study(studyRepository.getById(params.getStudyId())).build());
        return blacklist.entityToDto();
    }


    @Transactional
    public void deleteBlacklist(IdReqDto params) throws AuthenticationException {
        authCheck(params);
        blacklistRepository.delete(params.getStudyId(), params.getMemberId());
    }

    public Object getBlacklist(){
        List<Blacklist> blacklist = blacklistRepository.getByMemberId(getMemberId());
        List<Long> studies = new ArrayList<>();
        for (Blacklist black : blacklist) {
            studies.add(black.getStudy().getId());
        }
        return studies;
    }
    
    private void authCheck(IdReqDto params) throws AuthenticationException {
        if(getMemberId() != studyRepository.getById(params.getStudyId()).getStudyLeader()) {
            throw new AuthenticationException("블랙리스트는 스터디 장만 추가/삭제 가능합니다.");
        }
    }

    private Long getMemberId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return memberRepository.findByEmail(s).get().getId();
    }
}
