package it.tona.user_auth_api.connector.entity;

import lombok.Data;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "password_reset_tokens", schema = "USER_AUTH_API")
public class PasswordResetTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private LocalDateTime expiryDate;

    @ManyToOne
    private UserEntity user;


}
