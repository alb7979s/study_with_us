package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.util.FileUtil;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    private FileUtil fileUtil;

    public FileService(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

//    @Transactional
//    public Member setThumbnail(Member member, MultipartFile mf) throws IOException {
//        File file = fileUtil.makeDir("/member");
//        File f = fileUtil.makeName(mf.getOriginalFilename(), file);
//        mf.transferTo(f);	//파일 저장
//        // 썸네일 저장
//        Thumbnails.of(f)
//                .size(300, 200)
//                .toFile(new File(f.getParent(), "thumb_" + f.getName()));
//        member.setProfilepath(f.getParent());
//        member.setProfilename("thumb_" + f.getName());
//        return member;
//    }
//    @Transactional
//    public void setFiles(MultipartFile[] files, int boardNo) throws IOException, SQLException {
//        File file = fileUtil.makeDir("/file");
//        for(MultipartFile mf: files) {
//            // file 생성
//            String orgName = mf.getOriginalFilename();
//            File f = fileUtil.makeName(orgName, file);
//            mf.transferTo(f);
//            // db 저장
//            String contentType = fileUtil.getType(orgName);
//            MyFile myFile = new MyFile(boardNo, mf.getSize(), f.getParent(), orgName, f.getName(), contentType);
//            fileMapper.insertFile(myFile);
//        }
//        return;
//    }
//    public List<MyFile> getFiles(int boardNo) {
//        return fileMapper.getFile(boardNo);
//    }
}

