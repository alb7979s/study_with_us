package com.ssafy.study_with_us.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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

}
