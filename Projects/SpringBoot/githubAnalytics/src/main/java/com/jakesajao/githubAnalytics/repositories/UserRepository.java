package com.jakesajao.githubAnalytics.repositories;

import com.jakesajao.githubAnalytics.models.GitUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<GitUser,Long> {
    @Query("SELECT s FROM GitUser s WHERE s.email = ?1")
    Optional<GitUser> findGitUserByEmail(String email);
}
