package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.models.GitUserDetails;
import com.jakesajao.githubAnalytics.models.Repository;
import com.jakesajao.githubAnalytics.models.Role;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import com.jakesajao.githubAnalytics.services.HTTPConnections;
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
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;
    private HTTPConnections httpconnections = new HTTPConnections();

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
    @GetMapping("/admin")
    public ModelAndView admin() {
        String currentUserName = null;
        GitUser gitUser = null;
        currentUserName = getUsername();
        if (currentUserName!=null)
            gitUser = userRepository.findByEmail(currentUserName);
        else
            throw new NullPointerException();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        String name = gitUser.getFirstName() +" "+ gitUser.getLastName();
        String email = gitUser.getEmail();
        List<Repository> repoList = httpconnections.getUserRepo(gitUser.getFirstName()+gitUser.getLastName());
        modelAndView.addObject("repolist",repoList);
        modelAndView.addObject("gituser",name);
        modelAndView.addObject("git",gitUser.getFirstName()+gitUser.getLastName());

        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView home() {
        String currentUserName = null;
        GitUser gitUser = null;
        currentUserName = getUsername();
        if (currentUserName!=null)
         gitUser = userRepository.findByEmail(currentUserName);
        else
            throw new NullPointerException();
        String roleVal = getRole(gitUser.getRoles());

        if (roleVal.equals("ROLE_ADMIN")){
            System.out.println("ROLE 1: " + roleVal);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("ahome");
            String name = gitUser.getFirstName() +" "+ gitUser.getLastName();
            String email = gitUser.getEmail();
            List<Repository> repoList = httpconnections.getUserRepo(gitUser.getFirstName()+gitUser.getLastName());
            modelAndView.addObject("repolist",repoList);
            modelAndView.addObject("gituser",name);
            modelAndView.addObject("git",gitUser.getFirstName()+gitUser.getLastName());

            return modelAndView;

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        String name = gitUser.getFirstName() +" "+ gitUser.getLastName();
        String email = gitUser.getEmail();
        List<Repository> repoList = httpconnections.getUserRepo(gitUser.getFirstName()+gitUser.getLastName());
        modelAndView.addObject("repolist",repoList);
        modelAndView.addObject("gituser",name);
        modelAndView.addObject("git",gitUser.getFirstName()+gitUser.getLastName());

        return modelAndView;
    }
    private String getRole(Collection<Role> roleList){
        for(Role role: roleList) {

             return role.getName();
        }
        return null;
    }
    public String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        else
            return null;
    }
//    @RequestMapping(value="/login",method= RequestMethod.POST)
//    public ModelAndView index(@ModelAttribute("user") GitUser gituser) {
//        //System.out.println("Username from UI = "+gituser.getEmail());
//        GitUser gitUser = userRepository.findByEmail(gituser.getEmail());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home");
//        String name = gitUser.getFirstName() +" "+ gitUser.getLastName();
//        String email = gitUser.getEmail();
//        List<Repository> repoList = httpconnections.getUserRepo(gitUser.getFirstName()+gitUser.getLastName());
//        modelAndView.addObject("repolist",repoList);
//        modelAndView.addObject("gituser",name);
//        modelAndView.addObject("git",gitUser.getFirstName()+gitUser.getLastName());
//
//        return modelAndView;
//    }

}