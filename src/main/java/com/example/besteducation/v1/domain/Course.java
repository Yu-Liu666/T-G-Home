package com.example.besteducation.v1.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="t_course")
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String identifier;
    private String description;

    @OneToMany(mappedBy = "course")
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "course")
    private List<MailMessage> mailMessages;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public List<MailMessage> getMailMessages() {
        return mailMessages;
    }

    public void setMailMessages(List<MailMessage> mailMessages) {
        this.mailMessages = mailMessages;
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

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", description='" + description + '\'' +
                ", announcements=" + announcements +
                ", mailMessages=" + mailMessages +
                '}';
    }
}
