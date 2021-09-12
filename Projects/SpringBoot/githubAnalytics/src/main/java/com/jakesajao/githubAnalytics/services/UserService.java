package com.jakesajao.githubAnalytics.services;

import com.jakesajao.githubAnalytics.models.GitUser;
import dto.LoginFormDto;
import dto.UserRegistrationDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService {

    GitUser findByEmail(String email);
    @Query("select u from GitUser u where u.email = :email and u.password = :password")
    Optional<GitUser> validateCredentials(@Param("email")String email, @Param("password")String password);

    GitUser save(UserRegistrationDto registration);
//    UserDetailsService loadUserByUsername(LoginFormDto loginFormDto);
}