package com.example.solidtesting.security.service;

import com.example.solidtesting.security.model.Authentication;
import com.example.solidtesting.security.repository.AuthenticationRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PasswordHasher {
    private final String salt;
    private final AuthenticationRepository authenticationRepository;

    public PasswordHasher(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
        salt = RandomStringUtils.randomAlphanumeric(64);
    }

    public String hashPassword(String userId, String password, String algorithm) {
        String hashed = hashPasswordInternal(password, salt, algorithm);

        authenticationRepository.save(Authentication.builder()
                .password(hashed)
                .salt(salt)
                .userId(userId)
                .algorithm(algorithm)
                .build());

        return hashed;
    }

    public boolean authenticate(String userId, String password) {
        return authenticationRepository.findByUserId(userId)
                .map(auth -> authenticateInternal(auth, password))
                .orElse(false);
    }

    private boolean authenticateInternal(Authentication authentication, String userInput) {
        return StringUtils.equals(hashPasswordInternal(userInput, authentication.getSalt(), authentication.getAlgorithm()),
                authentication.getPassword());
    }

    private String hashPasswordInternal(String password, String salt, String algorithm) {
        if ("md5".equalsIgnoreCase(algorithm)) {
            return String.format("MD5:%s:%s", salt, password);
        }

        if ("sha".equalsIgnoreCase(algorithm)) {
            return String.format("SHA:%s:%s", salt, password);
        }

        return String.format("%s:%s:%s", algorithm, salt, password);
    }
}
