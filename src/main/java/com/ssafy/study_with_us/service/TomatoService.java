package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.Tomato;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.domain.repository.StudyRepository;
import com.ssafy.study_with_us.domain.repository.TomatoRepository;
import com.ssafy.study_with_us.dto.TomatoDto;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TomatoService {
    private final TomatoRepository tomatoRepository;
    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;

    public TomatoService(TomatoRepository tomatoRepository, MemberRepository memberRepository, StudyRepository studyRepository) {
        this.tomatoRepository = tomatoRepository;
        this.memberRepository = memberRepository;
        this.studyRepository = studyRepository;
    }

    //  date type 찾아봐야함
    public Object addTomato(TomatoDto params){
        // 여기서 tomato null이면 만들어주고 아니면 count + 1 해줌
        Tomato tomato = tomatoRepository.addTomato(TomatoDto.builder().memberId(getMemberId())
                .studyId(params.getStudyId()).date(LocalDate.now()).build());
        Tomato result = null;
        if(tomato == null) {
            result = tomatoRepository.save(Tomato.builder().tomatoCount(1)
                    .member(memberRepository.getById(getMemberId())).study(studyRepository.getById(params.getStudyId()))
                    .tomatoDate(LocalDate.now()).build());
        } else {
            result = tomatoRepository.save(Tomato.builder().id(tomato.getId()).tomatoCount(tomato.getTomatoCount()+1)
                    .member(tomato.getMember()).study(tomato.getStudy())
                    .tomatoDate(tomato.getTomatoDate()).build());
        }
        return TomatoDto.builder().id(result.getId()).date(result.getTomatoDate()).count(result.getTomatoCount()).studyId(result.getStudy().getId()).memberId(result.getMember().getId()).build();
    }

    private Long getMemberId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return memberRepository.findByEmail(s).get().getId();
    }

}
