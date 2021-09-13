package com.jakesajao.githubAnalytics.services;

import com.jakesajao.githubAnalytics.models.GitUser;
import dto.LoginFormDto;
import dto.UserRegistrationDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public interface UserService extends UserDetailsService {
    GitUser findByEmail(String email);
    GitUser save(UserRegistrationDto registration);
    UserDetails loadUserByUsername(String email);

}