package it.tona.user_auth_api.connector.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import it.tona.user_auth_api.connector.entity.PasswordResetTokenEntity;
import it.tona.user_auth_api.connector.entity.UserEntity;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {

    Optional<PasswordResetTokenEntity> findByToken(String token);

    List<PasswordResetTokenEntity> findAllByUser(UserEntity user);

    void deleteByUser(UserEntity user);

}
