package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    FileEntity save(FileEntity fileEntity);
}
