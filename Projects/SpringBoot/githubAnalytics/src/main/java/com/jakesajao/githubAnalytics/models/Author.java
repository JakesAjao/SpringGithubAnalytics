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
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public boolean site_admin;
}


class Tree{
    public String sha;
    public String url;
}

class Verification{
    public boolean verified;
    public String reason;
    public Object signature;
    public Object payload;
}

class Parent{
    public String sha;
    public String url;
    public String html_url;
}


