package com.jakesajao.githubAnalytics.models;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class GitUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    ///private String gender;
    private String email;
    private String password;
    private String mobilephone;


    @Transient
    private String role;

    public GitUser(){

    }
    public GitUser(long id,String firstName,String lastName, String email, String mobilephone,String roles) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        // this.gender = gender;
        this.email = email;
        this.mobilephone = mobilephone;
        this.role= roles;
    }

    public GitUser(long id,String firstName,String lastName, String email, String mobilephone,Collection<Role> roles) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        // this.gender = gender;
        this.email = email;
        this.mobilephone = mobilephone;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @Override
    public String toString() {
        return "GitUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                //", password='" + password + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", roles=" + role +
                '}';
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
    public Collection <Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }

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
