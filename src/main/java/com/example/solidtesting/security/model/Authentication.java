package com.example.solidtesting.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "authentication")
public class Authentication {
    @Id
    private String id;
    private String userId;
    private String password;
    private String salt;
    private String algorithm;
}
