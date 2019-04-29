package com.example.besteducation.v1.web;

import com.example.besteducation.v1.Service.CourseService;
import com.example.besteducation.v1.Service.UserService;
import com.example.besteducation.v1.domain.Announcement;
import com.example.besteducation.v1.domain.Course;
import com.example.besteducation.v1.domain.User;
import com.example.besteducation.v1.util.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/services")
public class ShowAndSearchClasses {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @GetMapping("/showclasses/{id}")
    public String showClasses(@PathVariable("id") Long id, Model model)
    {
        User user=userService.findById(id);
        List<Course> list=user.getCourses();
        model.addAttribute("courses",list);
        model.addAttribute("user",user);
        System.out.println(user.getRole());
        if(user.getRole()==1) return "displayClassesT";
        return "displayClasses";
    }

    @PostMapping("/addclass/{id}")
    public String addClass(@PathVariable("id") Long id, @RequestParam String newclass){
        User user=userService.findById(id);
        Course course=courseService.findByIdentifier(newclass);

        List<Course> list=user.getCourses();
        List<Course> newList= BlogUtils.copyCourse(list);
        newList.add(course);
        user.setCourses(newList);
        userService.createUser(user);
        return "redirect:/services/showclasses/"+user.getId();
    }

    @GetMapping("/ads")
    public String showAds(){
        return "/tutoring";
    }

    @PostMapping("/teacher/addcourse/{id}")
    public String postAnnouncement(@PathVariable("id") Long id,
                                   @ModelAttribute Course course,
                                   RedirectAttributes model){
        Course newcourse=new Course();
        newcourse.setDescription(course.getDescription());
        newcourse.setName(course.getName());
        newcourse.setIdentifier(course.getIdentifier());
        Course x=courseService.create(newcourse);
        User user=userService.findById(id);
        List<Course> list=user.getCourses();
        List<Course> newList= BlogUtils.copyCourse(list);
        newList.add(x);
        System.out.println(x);
        user.setCourses(newList);
        userService.createUser(user);

        String message = "Successfully add course! Please remember the course id is: " + newcourse.getIdentifier();
        model.addFlashAttribute("message",message);
        return "redirect:/services/showclasses/"+id;
    }
}
