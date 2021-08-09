package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    FileEntity save(FileEntity fileEntity);
    List<FileEntity> getByDataRoomId(Long dataRoomId);
    FileEntity getById(Long id);
}
