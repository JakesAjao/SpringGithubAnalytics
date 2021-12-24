package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.*;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import com.jakesajao.githubAnalytics.services.HTTPConnections;
import com.jakesajao.githubAnalytics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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
    @RequestMapping(value= "/user/edit/{email}/role/{role}", method = RequestMethod.GET)
    public String updateAuthor(@PathVariable("email")String email,@PathVariable("role")String role,
                               ModelMap model){
        GitUser user = userRepository.findByEmail(email);
        user.setRole(role);
        model.put("user", user);
        return "edituser";
}
    @RequestMapping(value= "/updateuser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") GitUser user, BindingResult result, ModelMap model, HttpServletRequest request){
        if (result.hasErrors()) {
            return "redirect:/user";
        }
        System.out.println("First NAME: "+user.getFirstName());
        System.out.println("Last NAME: "+user.getLastName());
        userService.UpdateUser(user);
        return  "redirect:/user";
    }
    @GetMapping("/user")
    public String getAuthor(Model model){
        return "redirect:/";
    }
    @GetMapping(value= "/user/delete/{userId}")
    public String deleteAuthor(@PathVariable("userId")Long userId,Model model){
        GitUser user = userRepository.getById(userId);
        userRepository.delete(user);
        System.out.println("User deleted successfully.");
        return "redirect:/user";
    }
    @GetMapping("/user/edit/{email}/name/{name}")
    public ModelAndView user(@PathVariable("email") String git, @PathVariable("name")String repo, HttpServletRequest request) {

        HttpSession session = request.getSession(true);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("repository");

        List<Committer> repository = httpconnections.repositoryByRepoName(git,repo);
        if (repository==null)
            modelAndView.addObject("repository","");
        else
            modelAndView.addObject("repository",repository);
        session.setAttribute("committerList", repository);
        modelAndView.addObject("gituser",git);
        return modelAndView;
    }

    @GetMapping("/repository/user/{git}/name/{repo}")
    public ModelAndView repository(@PathVariable("git") String git, @PathVariable("repo")String repo, HttpServletRequest request) {

        HttpSession session = request.getSession(true);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("repository");

        List<Committer> repository = httpconnections.repositoryByRepoName(git,repo);
        if (repository==null)
        modelAndView.addObject("repository","");
        else
            modelAndView.addObject("repository",repository);
        session.setAttribute("committerList", repository);
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
