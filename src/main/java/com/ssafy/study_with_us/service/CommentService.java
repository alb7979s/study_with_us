package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.Comment;
import com.ssafy.study_with_us.domain.repository.CommentRepository;
import com.ssafy.study_with_us.domain.repository.MemberRepository;
import com.ssafy.study_with_us.domain.repository.StudyRepository;
import com.ssafy.study_with_us.dto.CommentDto;
import com.ssafy.study_with_us.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;

    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository, StudyRepository studyRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.studyRepository = studyRepository;
    }

    public Object create(CommentDto params) {
        Comment result = commentRepository.save(Comment.builder()
                .content(params.getContent())
                .member(memberRepository.getById(getMemberId()))
                .study(studyRepository.getById(params.getStudyId()))
                .build());
        return result.entityToDto();
    }

    // 세상에.. spring data jpa로 save()할 때 pk 같으면 update 되는거 까먹고 있었다
    @Transactional
    public Object update(CommentDto params) {
        Comment comment = commentRepository.getById(params.getId());
        Comment result = commentRepository.save(Comment.builder()
                .id(params.getId())
                .content(params.getContent() == null ? comment.getContent() : params.getContent())
                .study(comment.getStudy())
                .member(comment.getMember())
                .reg_time(comment.getRegTime())
                .build());
        return result.entityToDto();
    }

    @Transactional
    public void delete(CommentDto params) {
        commentRepository.delete(commentRepository.getById(params.getId()));
    }

    public Object getComments(Long studyId){
        List<Comment> comments = commentRepository.getByStudy(studyRepository.getById(studyId));
        List<CommentDto> results = new ArrayList<>();
        for (Comment comment : comments) {
            results.add(comment.entityToDto());
        }
        return results;
    }
    private Long getMemberId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return memberRepository.findByEmail(s).get().getId();
    }
}
