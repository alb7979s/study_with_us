package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Comment;
import com.ssafy.study_with_us.domain.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment save(Comment comment);
    Comment getById(Long id);
    void delete(Comment comment);
    List<Comment> getByStudy(Study study);
}
