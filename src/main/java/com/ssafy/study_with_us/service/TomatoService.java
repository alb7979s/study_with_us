package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.repository.TomatoRepository;
import org.springframework.stereotype.Service;

@Service
public class TomatoService {
    private final TomatoRepository tomatoRepository;

    public TomatoService(TomatoRepository tomatoRepository) {
        this.tomatoRepository = tomatoRepository;
    }
}
