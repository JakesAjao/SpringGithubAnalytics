package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.models.Repository;
import com.jakesajao.githubAnalytics.models.Root;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedHashSet;

@Controller
public class DashboardController {
    @Autowired
    private UserRepository userRepository;
    final String Git_URL = "https://api.github.com";

    @RequestMapping(value="/index",method= RequestMethod.POST)
    public ModelAndView index(@ModelAttribute("user") GitUser gituser) {
        System.out.println("Username from UI = "+gituser.getEmail());
        GitUser gitUser = userRepository.findByEmail(gituser.getEmail());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        String name = gitUser.getFirstName() +" "+ gitUser.getLastName();
        ArrayList<Repository>  repoList = getUserRepo(gitUser.getFirstName()+gitUser.getLastName());
        modelAndView.addObject("gituser",name);
        modelAndView.addObject("repoList",repoList);
        return modelAndView;
    }

private ArrayList<Repository>  getUserRepo(String username) {

    ArrayList<Object> output = new ArrayList<>();
    ArrayList<Repository> repoList = new ArrayList<>();
    ArrayList<Integer> star_counts = new ArrayList<>();

System.out.println("Repo model username: "+username);
    String URL = Git_URL+"/users/" + username +"/repos?type=owner";

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent", "profile-analyzer");
    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    ResponseEntity<Root[]> repos = restTemplate.exchange(URL, HttpMethod.GET, entity, Root[].class);
    //System.out.println("Git hub Data: "+repos);

    Root[] arr = repos.getBody();
    String description =null;
    String name = null;
    for (Root repo : arr) {
        description = repo.getDescription();
        System.out.println("Git hub Data description: "+description);
        name = repo.getName();
        Repository repoData = new Repository(name,description);
        repoList.add(repoData);
    }
    //model.addAttribute("repolist",repoList);
    return repoList;

}


}
