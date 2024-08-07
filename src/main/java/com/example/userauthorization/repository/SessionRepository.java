package com.example.userauthorization.repository;

import com.example.userauthorization.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByTokenEquals(String token);
}
