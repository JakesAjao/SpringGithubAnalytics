package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.Committer;
import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.models.Repository;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import com.jakesajao.githubAnalytics.services.HTTPConnections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    private HTTPConnections httpconnections = new HTTPConnections();

    @GetMapping("/index")
    public String index(){
        return "index";
    }
//    @RequestMapping(value="/index",method= RequestMethod.POST)
//    public ModelAndView index(@ModelAttribute("user") GitUser gituser) {
//        System.out.println("Username from UI = "+gituser.getEmail());
//        GitUser gitUser = userRepository.findByEmail(gituser.getEmail());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        String name = gitUser.getFirstName() +" "+ gitUser.getLastName();
//        List<Repository> repoList = httpconnections.getUserRepo(gitUser.getFirstName()+gitUser.getLastName());
//        modelAndView.addObject("repolist",repoList);
//        modelAndView.addObject("gituser",name);
//
//        return modelAndView;
//    }

    @GetMapping("/repository/user/{git}/name/{repo}")
    public ModelAndView repository(@PathVariable("git") String git,@PathVariable("repo")String repo) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("repository");

        List<Committer> repository = httpconnections.repositoryByRepoName(git,repo);
        modelAndView.addObject("repository",repository);
        modelAndView.addObject("gituser",git);
        return modelAndView;
    }
    @GetMapping("/repository")
    public String repository(){
        return "repository";
    }

    @GetMapping("/login?logout")
    public String logout(){
        System.out.println("Log out...1");
        return "login";
    }

}
