package com.example.solidtesting.security;

import com.example.solidtesting.security.repository.AuthenticationRepository;
import com.example.solidtesting.security.service.PasswordHasher;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PasswordHasherTest {

    @Mock
    private AuthenticationRepository authenticationRepository;
    @InjectMocks
    private PasswordHasher passwordHasher;

    @ParameterizedTest
    @MethodSource("arguments")
    void hashPassword(String algorithm, String prefix) {
        String password = RandomStringUtils.randomAlphanumeric(16);
        String userId = UUID.randomUUID().toString();

        String hashed = passwordHasher.hashPassword(userId, password, algorithm);
        assertThat(hashed).startsWith(prefix).endsWith(password);
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("md5", "MD5"),
                Arguments.of("sha", "SHA"),
                Arguments.of("sha1", "sha1")
        );
    }
}