package com.jakesajao.githubAnalytics.models;

public class Repository {
    private long id;
    private String name;

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private String description;

    public Repository(long id,String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Repository(){

    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
