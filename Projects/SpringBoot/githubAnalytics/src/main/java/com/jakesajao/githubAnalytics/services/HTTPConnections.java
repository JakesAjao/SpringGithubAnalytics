package com.jakesajao.githubAnalytics.services;

import com.jakesajao.githubAnalytics.models.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
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
        String description = null;
        String name2 = null;
        long id = 0, i = 0;
        for (Root repo : arr) {
            id = repo.getId();
            description = repo.getDescription();
            name2 = repo.getName();
            Repository repoData = new Repository(id, name2, description);

            repoList.add(repoData);
        }
        //System.out.println("Git hub Data Repository html: " + repoList.size());
        return repoList;

    }
    public List<Committer> repositoryByRepoName(String username,String name) {
        try {
            System.out.println("client name= "+username);
            System.out.println("client gituser= "+name);
            ArrayList<Committer> repoList = new ArrayList<>();
            //System.out.println("Repository html model username: " + username);
            String URL = Git_URL + "/repos/" + username + "/" + name+"/commits?per_page=30";

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "profile-analyzer");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            System.out.println("Fetch the Repository json from Git.");
            ResponseEntity<Roots[]> repos = restTemplate.exchange(URL, HttpMethod.GET, entity, Roots[].class);
            System.out.println("Git hub Data: " + repos);

            Roots[] arr = repos.getBody();
            String email =null;
            String name2 = null;
            Date date = null;
            long id = 0;

            for (Roots roots : arr) {
                Commit commits  = roots.getCommit();
                Committer committer = commits.committer;
                id = committer.getId();
                name2 = committer.getName();
                email = committer.getEmail();
                date = committer.getDate();
                //Committer commitData = new Committer(name2,email,date);
                System.out.println("Git hub Data commitData: "+committer);
                repoList.add(committer);
            }
            return repoList;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }
        return null;
    }

}
