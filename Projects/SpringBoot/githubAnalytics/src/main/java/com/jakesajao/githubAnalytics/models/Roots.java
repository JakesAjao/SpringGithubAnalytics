package com.jakesajao.githubAnalytics.models;
import java.util.List;

public class Roots{
    public String sha;
    public String node_id;
    public Commit commit;
    public String url;
    public String html_url;
    public String comments_url;
    public Author author;
    public Committer committer;
    public List<Parent> parents;

    public Roots(){

    }

    public Roots(String sha, String node_id, Commit commit, String url, String html_url, String comments_url, Author author, Committer committer, List<Parent> parents) {
        this.sha = sha;
        this.node_id = node_id;
        this.commit = commit;
        this.url = url;
        this.html_url = html_url;
        this.comments_url = comments_url;
        this.author = author;
        this.committer = committer;
        this.parents = parents;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
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

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

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

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }
}
