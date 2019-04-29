package com.example.besteducation.v1.Service;

import com.example.besteducation.v1.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentsByCourseId(Long courseId);
    Comment save(Comment comment);
}
