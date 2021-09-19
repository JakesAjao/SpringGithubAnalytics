package com.jakesajao.githubAnalytics.services;

import com.jakesajao.githubAnalytics.models.Repository;
import com.jakesajao.githubAnalytics.models.Root;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class HTTPConnections {
    final String Git_URL = "https://api.github.com";
    public List<Repository> getUserRepo(String username) {

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
            //System.out.println("Git hub Data Repository: "+repoData);
            repoList.add(repoData);
        }
        //model.addAttribute("repolist",repoList);
        return repoList;

    }
    public Repository repositoryByRepoName(String username,String name) {
        try {
            System.out.println("Username from UI 2 = " + username);
            Repository repoList = new Repository();
            System.out.println("Repository html model username: " + username);
            String URL = Git_URL + "/repos/" + username + "/" + name;

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "profile-analyzer");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<Root> repos = restTemplate.exchange(URL, HttpMethod.GET, entity, Root.class);
            System.out.println("Git hub Data: " + repos);

            Root arr = repos.getBody();
            String description = null;
            String name2 = null;
            int id = 0, i = 0;
            //for (Root repo : arr) {
                id = ++i;
                description = arr.getDescription();
                name2 = arr.getName();
                Repository repoData = new Repository(id, name2, description);
                System.out.println("Git hub Data Repository html: " + repoData);
                //repoList.add(repoData);
            //}
            return repoList;
        } catch (Exception e) {
            System.out.println("Exception : "+e);
        }
        return null;
    }

}
