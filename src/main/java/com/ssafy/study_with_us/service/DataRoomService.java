package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.repository.DataRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class DataRoomService {
    private final DataRoomRepository dataRoomRepository;

    public DataRoomService(DataRoomRepository dataRoomRepository) {
        this.dataRoomRepository = dataRoomRepository;
    }


}
