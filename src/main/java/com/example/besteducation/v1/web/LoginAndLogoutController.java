package com.example.besteducation.v1.web;

import com.example.besteducation.v1.Service.UserService;
import com.example.besteducation.v1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
@Controller
public class LoginAndLogoutController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession httpSession,
                        RedirectAttributes attributes){
        User user=userService.findUserByUsernameAndPassword(username,password);
        System.out.println(user);
        if(user!=null){
            httpSession.setAttribute("user",user);
            user.setPassword(null);
            Long id=user.getId();
            return "redirect:/services/showclasses/"+id;
        }
        else{
            attributes.addFlashAttribute("message", "Your username or password is wrong");
            return "redirect:/login";
        }
    }
    @PostMapping("/signup")
    public String login(@ModelAttribute User user, RedirectAttributes attributes, HttpSession httpSession){
        String username = user.getUsername();
        User temp=userService.findUserByUsername(username);
        if(temp!=null)
        {
            attributes.addFlashAttribute("message","You should type in another username");
            return "redirect:/signup";
        }
        else{
            User u=new User();
            u.setPassword(user.getPassword());
            u.setRealNmae(user.getRealName());
            u.setRole(user.getRole());
            u.setSchool(user.getSchool());
            u.setTimeStamp(user.getTimeStamp());
            u.setUsername(user.getUsername());
            userService.createUser(u);
            httpSession.setAttribute("user",u);
            return "redirect:/services/showclasses/"+u.getId();
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
