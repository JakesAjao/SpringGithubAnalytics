package com.jakesajao.githubAnalytics.models;

public class Commit{
    public Author author;
    public Committer committer;
    public String message;
    public Tree tree;
    public String url;
    public int comment_count;
    public Verification verification;

//    public Commit(Author author, Committer committer, String message, Tree tree, String url, int comment_count, Verification verification) {
//        this.author = author;
//        this.committer = committer;
//        this.message = message;
//        this.tree = tree;
//        this.url = url;
//        this.comment_count = comment_count;
//        this.verification = verification;
//    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }



}
