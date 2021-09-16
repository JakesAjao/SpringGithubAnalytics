package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.models.GitUserDetails;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import com.jakesajao.githubAnalytics.services.UserService;
import com.jakesajao.githubAnalytics.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

//    @RequestMapping(value="/index",method= RequestMethod.POST)
//    public ModelAndView index(@ModelAttribute("user")GitUser gituser) {
//        System.out.println("Username from UI = "+gituser.getEmail());
//        GitUser gitUser = userRepository.findByEmail(gituser.getEmail());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        modelAndView.addObject("gituser",gitUser.getFirstName() +" "+ gitUser.getLastName());
//        return modelAndView;
//    }
    @GetMapping("/login")
    public String login(Model model){

        return "login";
    }
//    @RequestMapping(value="/index",method= RequestMethod.POST)
//    public ModelAndView index(@ModelAttribute("gitUser")GitUser gituser, HttpServletRequest request) {
//
//        // System.out.println("username from UI = "+username);
//        System.out.println("User from UI = "+gituser);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        modelAndView.addObject("user",gituser);
//
//        return modelAndView;
//    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}