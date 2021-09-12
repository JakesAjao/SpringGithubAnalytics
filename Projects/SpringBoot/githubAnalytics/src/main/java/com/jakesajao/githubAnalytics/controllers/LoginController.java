package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.services.UserService;
import dto.LoginFormDto;
import dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @ModelAttribute("login")
    public LoginFormDto loginFormDto() {
        return new LoginFormDto();
    }
//   @GetMapping("/login")
//   public String getDashboard(){
//        return "login";
//   }
//    @GetMapping("/")
//    public String root() {
//        return "index";
//    }
    @PostMapping("/")
    public String validateLoginInfo( @ModelAttribute("login") @Valid LoginFormDto loginForm, BindingResult bindingResult) {
        System.out.println("Entry 1|Email:"+loginForm.getEmail());
        Optional<GitUser> existing = userService.validateCredentials(loginForm.getEmail(),loginForm.getPassword());
        System.out.println("Entry 1|Email:"+existing.toString());
        if (existing != null) {
            System.out.println("Invalid Entry");
            bindingResult.rejectValue("email", null, "Invalid username or password");
        }
        if (bindingResult.hasErrors()) {
            return "login";
        }
        //model.addAttribute("user", loginForm.getEmail());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}
