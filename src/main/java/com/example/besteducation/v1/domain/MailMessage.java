package com.example.besteducation.v1.domain;

import javax.persistence.*;

@Entity
@Table(name="t_mail")
public class MailMessage {

    @Id
    @GeneratedValue
    private Long id;
    private String subjectLine;
    private String content;

    @ManyToOne
    private Course course;

    @ManyToOne
    private User user;

    public MailMessage() {

    }

    public MailMessage(String subjectLine, String content) {
        this.subjectLine = subjectLine;
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectLine() {
        return subjectLine;
    }

    public void setSubjectLine(String subjectLine) {
        this.subjectLine = subjectLine;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MailMessage{" +
                "id=" + id +
                ", subjectLine='" + subjectLine + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
