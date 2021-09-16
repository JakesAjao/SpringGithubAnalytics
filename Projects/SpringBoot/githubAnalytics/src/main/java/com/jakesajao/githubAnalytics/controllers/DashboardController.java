package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.models.Root;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
        modelAndView.addObject("gituser",gitUser.getFirstName() +" "+ gitUser.getLastName());
        return modelAndView;
    }
//    getRepoList(username:string): Observable<any> {
//        let headers = new HttpHeaders()
//                .set('Content-Type', 'application/json')
//        return this.http.get<any>(this.repoURL+'/users/'+username+'/repos?type=owner?per_page=100',{ headers });
//
//    }
@RequestMapping(method = RequestMethod.GET, value = "/getstarsperlang/{username}")
public @ResponseBody ArrayList<Object> getStarsPerLang(@PathVariable String username) {

    ArrayList<Object> output = new ArrayList<>();
    ArrayList<String> languages = new ArrayList<>();
    ArrayList<Integer> star_counts = new ArrayList<>();

    //String URL = Git_URL+"/users/" + username
    String URL = Git_URL+"/users/" + username +"/repos?type=owner?per_page=100";

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent", "profile-analyzer");
    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    ResponseEntity<Root[]> repos = restTemplate.exchange(URL, HttpMethod.GET, entity, Root[].class);

    Root[] arr = repos.getBody();

    for (Root repo : arr) {
        languages.add(repo.getLanguage());
    }

    // Remove Duplicates
    languages = new ArrayList<String>(new LinkedHashSet<String>(languages));

    // Synthesize output.
    for(String language : languages) {
        int count = 0;
        for(Root repo: arr){
            if(repo.getLanguage().equals(language)){
                count++;
            }
        }
        star_counts.add(count);
    }

    output.add(languages);
    output.add(star_counts);

    return output;

}


}
