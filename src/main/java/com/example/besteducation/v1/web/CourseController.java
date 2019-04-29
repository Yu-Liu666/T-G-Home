package com.example.besteducation.v1.web;

import com.example.besteducation.v1.Service.*;
import com.example.besteducation.v1.domain.*;
import com.example.besteducation.v1.util.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/announcements/{id}")
    public String showAnnouncements(@PathVariable("id") Long id, Model model, HttpSession session){
        Course course=courseService.findById(id);
//        List<Announcement> announcements=course.getAnnouncements();
//        model.addAttribute("announcements", announcements);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("course",course);
//        model.addAttribute("page",1);
        return "Announcement_Parents";
    }

    @GetMapping("/mailbox/{course_id}/{user_id}")
    public String showMailBox(@PathVariable("course_id") Long course_id,
                              @PathVariable("user_id") Long user_id,
                              Model model){
        Course course=courseService.findById(course_id);
        User user=userService.findById(user_id);

//        if(course == null||user==null) return "Announcement";
//        System.out.println("course_id: " + course_id);
//        System.out.println("user_id: " + user_id);
//
//        List<MailMessage> mailMessages1=course.getMailMessages();
//        List<MailMessage> mailMessages2=user.getMailMessages();
//        List<MailMessage> combinedMessages= BlogUtils.combine(mailMessages1,mailMessages2);
//        model.addAttribute("emailmessages",combinedMessages);
        model.addAttribute("user",user);
        model.addAttribute("course",course);
        return "MailBox";
    }

    @PostMapping("/mailbox/{course_id}/{user_id}")
    public String writeMail(@PathVariable("course_id") Long course_id,
                            @PathVariable("user_id") Long user_id,
                            @RequestParam("recipient-username") String recipient_username,
                            @RequestParam("subject-line") String subjectLine,
                            @RequestParam("content") String content,
                            Model model){

        Course course=courseService.findById(course_id);
        User user=userService.findUserByUsername(recipient_username);
        List<MailMessage> newArr=BlogUtils.copyMail(course.getMailMessages());
        MailMessage mailMessage=new MailMessage();
        mailMessage.setSubjectLine(subjectLine);
        mailMessage.setContent(content);
        mailMessage.setCourse(course);
        mailMessage.setUser(user);
        newArr.add(mailMessage);
        course.setMailMessages(newArr);
        courseService.create(course);

        List<MailMessage> newArr2=BlogUtils.copyMail(user.getMailMessages());
        newArr2.add(mailMessage);
        user.setMailMessages(newArr2);
        userService.createUser(user);


        mailService.save(mailMessage);
        return "redirect:/course/mailbox/"+course_id+"/"+user_id;
    }

    @PostMapping("/announcements/teacher/{id}")
    public String postAnnouncement(@PathVariable("id") Long id,
                                   @RequestParam("subject-line") String subjectLine,
                                   @RequestParam("content") String content,
                                   Model model){
        Announcement announcement=new Announcement();
        announcement.setContent(content);
        announcement.setHeaderLine(subjectLine);
        Course course=courseService.findById(id);
        announcement.setCourse(course);
        announcementService.save(announcement);
        course.getAnnouncements().add(announcement);
        courseService.create(course);
        return "redirect:/course/announcements/teacher/"+course.getId();
    }

    @GetMapping("/announcements/teacher/{id}")
    public String showAnnouncements2(@PathVariable("id") Long id,
                                     Model model,
                                     HttpSession session){
        Course course=courseService.findById(id);
        List<Announcement> announcements=course.getAnnouncements();
        model.addAttribute("announcements", announcements);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("course",course);
        return "Announcement_Teacher";
    }

    @GetMapping("/channel/{course_id}")
    public String channel(@PathVariable("course_id") Long course_id,
                          Model model){
        model.addAttribute("course", courseService.findById(course_id));
        return "channel3";
    }

    @GetMapping("/comments/{course_id}")
    public String comments(@PathVariable("course_id") Long courseId, Model model){
        model.addAttribute("comments", commentService.listCommentsByCourseId(courseId));
        model.addAttribute("course", courseService.findById(courseId));
        return "channel3 :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long courseId = comment.getCourse().getId();
        comment.setCourse(courseService.findById(courseId));
        User user = (User) session.getAttribute("user");
        if (user.getRole() == 1) {
            comment.setAvatar("/images/yellow.jpg");
            comment.setAdmin(true);
        } else {
            comment.setAvatar("/images/yellow2.jpg");
        }
        commentService.save(comment);
        return "redirect:/comments/" + courseId;
    }

    @GetMapping("/grade/{id}")
    public String showGrade(@PathVariable("id") Long id, Model model, HttpSession session){
        Course course=courseService.findById(id);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("course",course);
        return "grade";
    }

    @GetMapping("/comment/{id}")
    public String showComment(@PathVariable("id") Long id, Model model, HttpSession session){
        Course course=courseService.findById(id);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("course",course);
        return "comment";
    }

    @GetMapping("/fragment/{course_id}/{user_id}")
    public String getMails(@PathVariable("course_id") Long course_id,
                              @PathVariable("user_id") Long user_id,
                              Model model){
        Course course=courseService.findById(course_id);
        User user=userService.findById(user_id);

        List<MailMessage> mailMessages1=course.getMailMessages();
        List<MailMessage> mailMessages2=user.getMailMessages();
        List<MailMessage> combinedMessages= BlogUtils.combine(mailMessages1,mailMessages2);
        model.addAttribute("emailmessages",combinedMessages);
        model.addAttribute("user",user);
        model.addAttribute("course",course);
        return "MailBox :: mailList";
    }

    @GetMapping("/ann/{id}")
    public String showAnn(@PathVariable("id") Long id, Model model, HttpSession session){
        Course course=courseService.findById(id);
        List<Announcement> announcements=course.getAnnouncements();
        model.addAttribute("announcements", announcements);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("course",course);
//        model.addAttribute("page",1);
        return "Announcement_Parents :: annList";
    }

}
