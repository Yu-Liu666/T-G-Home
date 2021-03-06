package com.example.besteducation.v1.dao;

import com.example.besteducation.v1.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCourseIdAndParentCommentNull(Long courseId, Sort sort);
}
