package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.models.Repository;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import com.jakesajao.githubAnalytics.services.HTTPConnections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private UserRepository userRepository;

    private HTTPConnections httpconnections = new HTTPConnections();

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping(value="/index",method= RequestMethod.POST)
    public ModelAndView index(@ModelAttribute("user") GitUser gituser) {
        System.out.println("Username from UI = "+gituser.getEmail());
        GitUser gitUser = userRepository.findByEmail(gituser.getEmail());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        String name = gitUser.getFirstName() +" "+ gitUser.getLastName();
        List<Repository> repoList = httpconnections.getUserRepo(gitUser.getFirstName()+gitUser.getLastName());
        modelAndView.addObject("repolist",repoList);
        modelAndView.addObject("gituser",name);

        return modelAndView;
    }

    @RequestMapping(value="/repository",method= RequestMethod.POST)
    public ModelAndView repository(@ModelAttribute("user") GitUser gituser,
                                   @PathVariable("name2")String name2) {
        System.out.println("Username from UI = "+gituser.getEmail());
        GitUser gitUser = userRepository.findByEmail(gituser.getEmail());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        String name = gitUser.getFirstName() +" "+ gitUser.getLastName();
        List<Repository> repository = httpconnections.repositoryByRepoName(gitUser.getFirstName()+gitUser.getLastName(),name2);
        modelAndView.addObject("repository",repository);
        modelAndView.addObject("gituser",name);

        return modelAndView;
    }

}
