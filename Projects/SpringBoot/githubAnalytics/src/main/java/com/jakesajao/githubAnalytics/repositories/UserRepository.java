package com.jakesajao.githubAnalytics.repositories;

import com.jakesajao.githubAnalytics.models.GitUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<GitUser,Long> {
    GitUser findByEmail(String email);
    @Query("select u from GitUser u where u.email = :email and u.password = :password")
    Optional<GitUser> validateCredentials(@Param("email")String email, @Param("password")String password);
}
