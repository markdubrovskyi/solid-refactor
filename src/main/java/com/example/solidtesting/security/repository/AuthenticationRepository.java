package com.example.solidtesting.security.repository;

import com.example.solidtesting.security.model.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, String> {

    Optional<Authentication> findByUserId(String userId);
}
