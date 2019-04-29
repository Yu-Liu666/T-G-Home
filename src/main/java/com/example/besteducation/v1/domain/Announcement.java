package com.example.besteducation.v1.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_annoucement")
public class Announcement {

    @Id
    @GeneratedValue
    private Long id;
    private String headerLine;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    @ManyToOne
    private Course course;

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public Announcement() {
    }

    public Announcement(String headerLine, String content, Date timeStamp) {
        this.headerLine = headerLine;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeaderLine() {
        return headerLine;
    }

    public void setHeaderLine(String headerLine) {
        this.headerLine = headerLine;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", headerLine='" + headerLine + '\'' +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
