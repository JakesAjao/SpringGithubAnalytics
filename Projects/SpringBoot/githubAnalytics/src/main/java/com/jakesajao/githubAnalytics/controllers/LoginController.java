package com.jakesajao.githubAnalytics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getDashboard(){
        return "login";
    }
}
