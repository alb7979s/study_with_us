package com.ssafy.study_with_us.util;

import com.ssafy.study_with_us.domain.entity.DataRoom;
import com.ssafy.study_with_us.domain.entity.FileEntity;
import com.ssafy.study_with_us.dto.FileDto;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {

    public File makeDir(String loc) {
        String uploadRoot = "c:/sweet_tomato/upload";
        String path = loc + new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
        File file = new File(uploadRoot + path);
        if(!file.exists()) file.mkdirs();
        return file;
    }
    public String getType(String name) {
        int index = name.lastIndexOf(".");
        if (index != -1) {
            return name.substring(index);
        }
        return "";
    }
    public File makeName(String name, File file) {
        String ext = getType(name);
        File f = new File(file.getPath(), UUID.randomUUID() + ext);
        return f;
    }
    public String setThumbnail(File f) throws IOException {
        // 썸네일 저장
        Thumbnails.of(f)
                .size(300, 200)
                .toFile(new File(f.getParent(), "thumb_" + f.getName()));
        return "thumb_" + f.getName();
    }

    public File setImage(MultipartFile mf) throws IOException {
        File f = makeName(mf.getOriginalFilename(), makeDir(""));
        mf.transferTo(f);
        return f;
    }

    public List<FileEntity> setFiles(List<MultipartFile> files, DataRoom dataRoom) throws IOException {
        File file = makeDir("/file");
        List<FileEntity> fileEntities = new ArrayList<>();
        for(MultipartFile mf: files) {
            if(mf.getSize() == 0) continue;
            // file 생성
            String orgName = mf.getOriginalFilename();
            File f = makeName(orgName, file);
            mf.transferTo(f);
            // db 저장
            String contentType = getType(orgName);
            fileEntities.add(FileEntity.builder().sysName(f.getName()).orgName(orgName).path(f.getParent() + "\\")
                    .fileSize(mf.getSize()).fileType(contentType).regTime(LocalDateTime.now()).dataRoom(dataRoom).build());
        }
        return fileEntities;
    }
}
