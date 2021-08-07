package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.DataRoom;
import com.ssafy.study_with_us.domain.entity.FileEntity;
import com.ssafy.study_with_us.domain.repository.FileRepository;
import com.ssafy.study_with_us.dto.FileDto;
import com.ssafy.study_with_us.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;
    private final FileUtil fileUtil;

    public FileService(FileRepository fileRepository, FileUtil fileUtil) {
        this.fileRepository = fileRepository;
        this.fileUtil = fileUtil;
    }

    public List<FileDto> create(List<MultipartFile> files, DataRoom dataRoom) throws IOException {
        List<FileEntity> fileEntities = fileUtil.setFiles(files, dataRoom);
        List<FileDto> results = new ArrayList<>();
        for (FileEntity fileEntity : fileEntities) {
            results.add(fileRepository.save(fileEntity).entityToDto());
        }
        return results;
    }
}
