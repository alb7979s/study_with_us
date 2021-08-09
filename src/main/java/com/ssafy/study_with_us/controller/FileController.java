package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.entity.FileEntity;
import com.ssafy.study_with_us.service.FileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") Long fileId) throws IOException {
        FileEntity file = fileService.getFile(fileId);
        Path path = new File(file.getPath() + file.getSysName()).toPath();
        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
