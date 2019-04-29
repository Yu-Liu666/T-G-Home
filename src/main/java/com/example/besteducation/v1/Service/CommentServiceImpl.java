package com.example.besteducation.v1.Service;

import com.example.besteducation.v1.dao.CommentRepository;
import com.example.besteducation.v1.domain.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentsByCourseId(Long courseId) {
        Sort sort=new Sort(Sort.Direction.ASC,"createTime");
        List<Comment> comments=commentRepository.findByCourseIdAndParentCommentNull(courseId,sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        Long parentId=comment.getParentComment().getId();
        if(parentId!=-1)
        {
            comment.setParentComment(commentRepository.findOne(parentId));
        }
        else
            comment.setParentComment(null);
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    public List<Comment> eachComment(List<Comment> comments)
    {
        List<Comment> list=new ArrayList<>();
        for(Comment c:comments)
        {
            Comment newComment=new Comment();
            BeanUtils.copyProperties(c,newComment);
            list.add(newComment);
        }
        combineChildren(list);
        return list;
    }

    private List<Comment> replyComments=new ArrayList<>();
    public void combineChildren(List<Comment> comments)
    {
        for(Comment comment:comments)
        {
            List<Comment> replys=comment.getReplyComments();
            for(Comment reply:replys)
            {
                dfs(reply);
            }
            comment.setReplyComments(replyComments);
            replyComments=new ArrayList<>();
        }
    }

    public void dfs(Comment comment)
    {
        replyComments.add(comment);
        if(comment.getReplyComments().size()==0) return;
        for(Comment c:comment.getReplyComments())
        {
            dfs(c);
        }
    }
}