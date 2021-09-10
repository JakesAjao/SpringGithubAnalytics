package com.jakesajao.githubAnalytics.models;

import javax.persistence.*;

@Entity
public class GitUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String gender;
    private String email;
    private String password;
    private String mobilephone;
    public GitUser() {
    }
    public GitUser(String name, String password,String gender, String email, String mobilephone) {
        //this.id = id;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.mobilephone = mobilephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilephone() {
        return this.mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }


}
