package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.Tomato;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.domain.repository.TomatoRepository;
import com.ssafy.study_with_us.dto.TomatoDto;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class TomatoService {
    private final TomatoRepository tomatoRepository;
    private final MemberRepository memberRepository;

    public TomatoService(TomatoRepository tomatoRepository, MemberRepository memberRepository) {
        this.tomatoRepository = tomatoRepository;
        this.memberRepository = memberRepository;
    }

//  date type 찾아봐야함
    public Object addTomato(TomatoDto params){
        // 여기서 tomato null이면 만들어주고 아니면 count + 1 해줌
        Tomato tomato = tomatoRepository.addTomato(TomatoDto.builder().memberId(getMemberId()).studyId(params.getStudyId())
                .date(null).build());
        Tomato result = null;
        if(tomato == null) {
            // study, member 잘 들어가는지 확인해줘야함
            result = tomatoRepository.save(Tomato.builder().id(tomato.getId()).tomatoCount(1)
                    .tomatoDate(null).member(tomato.getMember()).study(tomato.getStudy()).build());
        } else {
            result = tomatoRepository.save(Tomato.builder().id(tomato.getId()).tomatoCount(tomato.getTomatoCount()+1)
                    .tomatoDate(null).member(tomato.getMember()).study(tomato.getStudy()).build());
        }
        return result;
    }

    private Long getMemberId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return memberRepository.findByEmail(s).get().getId();
    }

}
