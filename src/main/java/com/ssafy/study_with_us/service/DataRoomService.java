package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.DataRoom;
import com.ssafy.study_with_us.domain.repository.DataRoomRepository;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.domain.repository.StudyRepository;
import com.ssafy.study_with_us.dto.DataRoomDto;
import com.ssafy.study_with_us.dto.FileDto;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataRoomService {
    private final DataRoomRepository dataRoomRepository;
    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;
    private final FileService fileService;

    public DataRoomService(DataRoomRepository dataRoomRepository, MemberRepository memberRepository, StudyRepository studyRepository, FileService fileService) {
        this.dataRoomRepository = dataRoomRepository;
        this.memberRepository = memberRepository;
        this.studyRepository = studyRepository;
        this.fileService = fileService;
    }

    public DataRoomDto create(DataRoomDto params, List<MultipartFile> files) throws IOException {
        // 자료실(글) 저장
        DataRoom dataRoom = saveDataRoom(params);
        // 파일 저장
        fileService.create(files, dataRoom);
        return dataRoom.entityToDto();
    }

    @Transactional
    public DataRoomDto update(DataRoomDto params, List<MultipartFile> files) throws IOException {
        DataRoom getDataRoom = dataRoomRepository.getById(params.getId());
        DataRoom dataRoom = saveDataRoom(DataRoomDto.builder()
                .id(getDataRoom.getId())
                .subject(params.getSubject() == null ? getDataRoom.getSubject() : params.getSubject())
                .content(params.getContent() == null ? getDataRoom.getContent() : params.getContent())
                .member(getDataRoom.getMember().entityToDto())
                .studyId(getDataRoom.getStudy().getId())
                .build());
        // file 수정 처리
        // 게시물의 모든 첨부파일 목록 삭제하고, 받아온 데이터 다시 추가
        dataRoomRepository.deleteFiles(dataRoom.getId());
        fileService.create(files, dataRoom);
        return dataRoom.entityToDto();
    }

    public Object getDetail(Long dataRoomId){
        DataRoom dataRoom = dataRoomRepository.getById(dataRoomId);
        List<FileDto> files = fileService.getFiles(dataRoomId);
        return dataRoom.entityToDto(files);
    }

    public Object getDataRoomList(Long studyId){
        List<DataRoom> dataRooms = dataRoomRepository.getByStudyId(studyId);
        // results 반환하는거 람다식으로 가능할듯? or 함수형 프로그래밍으로
        List<DataRoomDto> results = new ArrayList<>();
        for (DataRoom dataRoom : dataRooms) {
            results.add(dataRoom.entityToDto());
        }
        return results;
    }

    private DataRoom saveDataRoom(DataRoomDto params) {
        return dataRoomRepository.save(DataRoom.builder()
                .id(params.getId()).subject(params.getSubject()).content(params.getContent())
                .member(memberRepository.getById(getMemberId()))
                .study(studyRepository.getById(params.getStudyId())).build());
    }

    private Long getMemberId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return memberRepository.findByEmail(s).get().getId();
    }
}
