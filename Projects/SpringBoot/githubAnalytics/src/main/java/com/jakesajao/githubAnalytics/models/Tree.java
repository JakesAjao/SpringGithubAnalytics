package com.jakesajao.githubAnalytics.models;

public class Tree{
    public String sha;

    public String url;

//    public Tree(String sha, String url) {
//        this.sha = sha;
//        this.url = url;
//    }

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

}