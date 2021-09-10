package com.jakesajao.githubAnalytics;

import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class BoostrapData {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            GitUser mariam = new GitUser("Mariam","mariam", "Female", "mariam@gmail.com","07066690519");

            repository.saveAll(List.of(mariam));

        };
    }
}

