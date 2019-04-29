package com.example.besteducation.v1.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private boolean admin;
    private String avatar;
    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments=new ArrayList<>();

    @ManyToOne
    private Comment parentComment;

    public Comment() {
    }

    public Comment(String name, String content, Date createTime, Course course, List<Comment> replyComments, Comment parentComment) {
        this.name = name;
        this.content = content;
        this.createTime = createTime;
        this.course = course;
        this.replyComments = replyComments;
        this.parentComment = parentComment;
    }

    public boolean isAdmin() {
        return admin;
    }


    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", course=" + course +
                ", replyComments=" + replyComments +
                ", parentComment=" + parentComment +
                '}';
    }
}
