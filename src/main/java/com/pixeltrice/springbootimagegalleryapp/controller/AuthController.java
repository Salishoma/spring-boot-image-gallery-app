package com.pixeltrice.springbootimagegalleryapp.controller;

import com.pixeltrice.springbootimagegalleryapp.model.Admin;
import com.pixeltrice.springbootimagegalleryapp.model.User;
import com.pixeltrice.springbootimagegalleryapp.service.AdminService;
import com.pixeltrice.springbootimagegalleryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private UserService userService;
    private AdminService adminService;


    @Autowired
    public AuthController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping( "/signup")
    public String signUpForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") User user, Model model, HttpSession session){
        if(userService.hasAccount(user)){
            model.addAttribute("invalid", "User already exists");
            return "signup";
        }
        User newUser = userService.createUser(user);
        session.setAttribute("user", user);
        return "redirect:/media/" + newUser.getId();
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

//    @GetMapping("/")
//    public String enterForm(Model model){
////        model.addAttribute("user", new User());
//        return "Media";
//    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Admin admin, Model model, HttpSession session){


        if(user.getEmail().endsWith("@admin.com")){
            admin.setEmail(user.getEmail());
            admin.setPassword(user.getPassword());
        }
        if(adminService.hasAccount(admin) && adminService.isCorrect(admin)){
            System.out.println("Successfully entered");
            session.setAttribute("admin", admin);
            return "redirect:/media";
        }
        if(userService.hasAccount(user)){
            User userInDB = userService.userInDB(user);
            if(!userInDB.getPassword().equals(user.getPassword())){
                return "login";
            }
            session.setAttribute("user", user);
            return "redirect:/media/" + userInDB.getId();
        }

        model.addAttribute("invalid", "Invalid email or password");
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";  //Where you go after logout here.
    }
}