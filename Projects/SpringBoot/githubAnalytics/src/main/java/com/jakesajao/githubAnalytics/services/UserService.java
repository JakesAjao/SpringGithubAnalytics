package com.jakesajao.githubAnalytics.services;

import com.jakesajao.githubAnalytics.models.GitUser;
import dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    GitUser findByEmail(String email);

    GitUser save(UserRegistrationDto registration);
}