package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.Committer;
import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.models.Repository;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import com.jakesajao.githubAnalytics.services.HTTPConnections;
import com.jakesajao.githubAnalytics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/repository/user/{git}/name/{repo}")
    public ModelAndView repository(@PathVariable("git") String git,@PathVariable("repo")String repo) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("repository");

        List<Committer> repository = httpconnections.repositoryByRepoName(git,repo);
        modelAndView.addObject("repository",repository);
        modelAndView.addObject("gituser",git);
        return modelAndView;
    }
    @RequestMapping(value = "/listcommitters", method = RequestMethod.GET)
    public String listBooks(
            Model model,@RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("git") String git,
            @RequestParam("repo") String repo) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        List<Committer> repository = httpconnections.repositoryByRepoName(git,repo);
        Page<Committer> bookPage = userService.findPaginated(PageRequest.of(currentPage - 1, pageSize),
                repository);

        model.addAttribute("bookPage", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("gituser",git);

        }

        return "listcommitters.html";
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
