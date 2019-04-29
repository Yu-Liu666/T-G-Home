package com.example.besteducation.v1.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private int role;
    private String school;
    private String realName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    @ManyToMany
    private List<Course> courses=new ArrayList<Course>();

    @OneToMany(mappedBy = "user")
    private List<MailMessage> mailMessages;

    public User() {
    }

    public User(String username, String password, int role, String school, String realName, Date timeStamp) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.school = school;
        this.realName = realName;
        this.timeStamp = timeStamp;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public String getSchool() {
        return school;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealNmae(String realName) {
        this.realName = realName;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", school='" + school + '\'' +
                ", realNmae='" + realName + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
