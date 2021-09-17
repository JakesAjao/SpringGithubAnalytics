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
import java.util.List;

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
        List<Repository> repoList = getUserRepo(gitUser.getFirstName()+gitUser.getLastName());
        modelAndView.addObject("repolist",repoList);
        modelAndView.addObject("gituser",name);

        return modelAndView;
    }

private List<Repository>  getUserRepo(String username) {

    ArrayList<Repository> repoList = new ArrayList<>();

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
    int id = 0;
    for (Root repo : arr) {
        id  =  ++id;
        description = repo.getDescription();
        name = repo.getName();
        Repository repoData = new Repository(id,name,description);
        System.out.println("Git hub Data Repository: "+repoData);
        repoList.add(repoData);
    }
    //model.addAttribute("repolist",repoList);
    return repoList;

}


}
