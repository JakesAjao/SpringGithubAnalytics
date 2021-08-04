package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.AUGUST;
import static java.util.Calendar.JANUARY;
@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student(21, "Mariam", "mariam.jamal@gmail.com", LocalDate.of(2000,
                    AUGUST, 4));
            Student alex = new Student(17, "Alex", "alex.jamal@gmail.com", LocalDate.of(2004,
                    AUGUST, 4));

            repository.saveAll(List.of(mariam,alex));

        };
    }
}
