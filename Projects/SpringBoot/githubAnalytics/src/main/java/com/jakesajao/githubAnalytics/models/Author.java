package com.jakesajao.githubAnalytics.models;

import java.util.Date;
import java.util.List;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */


public class Author{
    public String name;
    public String email;
    public Date date;
    public String login;
    public int id;
    public String node_id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;

//    public Author(String name, String email, Date date, String login, int id, String node_id, String avatar_url, String gravatar_id, String url, String html_url, String followers_url, String following_url, String gists_url, String starred_url, String subscriptions_url, String organizations_url, String repos_url, String events_url, String received_events_url, String type, boolean site_admin) {
//        this.name = name;
//        this.email = email;
//        this.date = date;
//        this.login = login;
//        this.id = id;
//        this.node_id = node_id;
//        this.avatar_url = avatar_url;
//        this.gravatar_id = gravatar_id;
//        this.url = url;
//        this.html_url = html_url;
//        this.followers_url = followers_url;
//        this.following_url = following_url;
//        this.gists_url = gists_url;
//        this.starred_url = starred_url;
//        this.subscriptions_url = subscriptions_url;
//        this.organizations_url = organizations_url;
//        this.repos_url = repos_url;
//        this.events_url = events_url;
//        this.received_events_url = received_events_url;
//        this.type = type;
//        this.site_admin = site_admin;
//    }

    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public boolean site_admin;
}
class Parent{
    public String sha;
    public String url;
    public String html_url;


    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
//    public Parent(String sha, String url, String html_url) {
//        this.sha = sha;
//        this.url = url;
//        this.html_url = html_url;
//    }

}


