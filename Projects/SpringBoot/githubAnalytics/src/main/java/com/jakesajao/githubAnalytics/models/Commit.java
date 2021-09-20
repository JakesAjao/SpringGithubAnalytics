package com.jakesajao.githubAnalytics.models;

public class Commit{
    public Author author;
    public Committer committer;
    public String message;
    public Tree tree;
    public String url;
    public int comment_count;
    public Verification verification;
}
