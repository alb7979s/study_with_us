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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Object addBlacklist(IdReqDto params) {
        // 스터디 멤버 삭제
        studyMemberRefRepository.withdraw(params);
        // 블랙리스트 추가
        Blacklist blacklist = blacklistRepository.save(Blacklist.builder()
                .member(memberRepository.getById(params.getMemberId()))
                .study(studyRepository.getById(params.getStudyId())).build());
        return blacklist.entityToDto();
    }

    @Transactional
    public void deleteBlacklist(Long studyId){
        blacklistRepository.delete(studyId, getMemberId());
    }

    public Object getBlacklist(){
        List<Blacklist> blacklist = blacklistRepository.getByMemberId(getMemberId());
        List<Long> studies = new ArrayList<>();
        for (Blacklist black : blacklist) {
            studies.add(black.getStudy().getId());
        }
        return studies;
    }

    private Long getMemberId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return memberRepository.findByEmail(s).get().getId();
    }
}
