package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.Tomato;
import com.ssafy.study_with_us.domain.entity.TomatoPlan;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.domain.repository.StudyRepository;
import com.ssafy.study_with_us.domain.repository.TomatoPlanRepository;
import com.ssafy.study_with_us.domain.repository.TomatoRepository;
import com.ssafy.study_with_us.dto.TomatoDto;
import com.ssafy.study_with_us.dto.TomatoPlanDto;
import com.ssafy.study_with_us.dto.TomatoResDto;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TomatoService {
    private final TomatoRepository tomatoRepository;
    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;
    private final TomatoPlanRepository tomatoPlanRepository;

    public TomatoService(TomatoRepository tomatoRepository, MemberRepository memberRepository, StudyRepository studyRepository, TomatoPlanRepository tomatoPlanRepository) {
        this.tomatoRepository = tomatoRepository;
        this.memberRepository = memberRepository;
        this.studyRepository = studyRepository;
        this.tomatoPlanRepository = tomatoPlanRepository;
    }

    public Object addTomato(TomatoDto params){
        // 여기서 tomato null이면 만들어주고 아니면 count + 1 해줌
        Tomato tomato = tomatoRepository.searchTomato(TomatoDto.builder().memberId(getMemberId())
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
    //member
    public TomatoResDto getTomatoes(){
        return TomatoResDto.builder().totalSum(tomatoRepository.getTotalSum()).relevantSum(tomatoRepository.getRelevantSum(getMemberId()))
                .tomatoes(getTomatoDtos(tomatoRepository.getTomatoes(getMemberId()))).build();
    }
    //study
    public TomatoResDto getTomatoes(TomatoDto params){
        return TomatoResDto.builder().totalSum(tomatoRepository.getTotalSum()).relevantSum(tomatoRepository.getRelevantSum(params))
                .tomatoes(getTomatoDtos(tomatoRepository.getTomatoes(params))).build();
    }
    public TomatoResDto getTodayTomatoes(TomatoDto params){
        return TomatoResDto.builder().totalSum(tomatoRepository.getTotalSum()).relevantSum(tomatoRepository.getRelevantSum(params))
                .tomatoes(getTomatoDtos(tomatoRepository.getTodayTomatoes(params))).build();
    }

    public TomatoPlanDto addGoal(TomatoPlanDto params){
        return tomatoPlanRepository.save(TomatoPlan.builder().goalTomato(params.getGoalTomato()).goalTime(params.getGoalTime()).study(studyRepository.getById(params.getStudyId())).tomatoDate(LocalDate.now()).build()).entityToDto();
    }

    public TomatoPlanDto updateGoal(TomatoPlanDto params){
        TomatoPlan tomatoPlan = tomatoPlanRepository.getById(params.getTomatoPlanId());
        return tomatoPlanRepository.save(TomatoPlan.builder()
                .id(params.getTomatoPlanId())
                .goalTomato(params.getGoalTomato() == null ? tomatoPlan.getGoalTomato() : params.getGoalTomato())
                .goalTime(params.getGoalTime() == null ? tomatoPlan.getGoalTime() : params.getGoalTime())
                .study(tomatoPlan.getStudy())
                .tomatoDate(LocalDate.now()).build()).entityToDto();
    }

    public List<TomatoPlanDto> getGoal(Long studyId){
        List<TomatoPlan> tomatoPlans = tomatoPlanRepository.getByStudyId(studyId);
        List<TomatoPlanDto> results = new ArrayList<>();
        for (TomatoPlan tomatoPlan : tomatoPlans) {
            results.add(tomatoPlan.entityToDto());
        }
        return results;
    }
    private List<TomatoDto> getTomatoDtos(List<Tomato> tomatoes) {
        List<TomatoDto> results = new ArrayList<>();
        for (Tomato tomato : tomatoes) {
            results.add(tomato.entityToDto());
        }
        return results;
    }

    private Long getMemberId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return memberRepository.findByEmail(s).get().getId();
    }

}
