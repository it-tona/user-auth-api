package it.tona.user_auth_api.connector.repository;

import java.util.Optional;

import it.tona.user_auth_api.connector.entity.RefreshTokenEntity;
import it.tona.user_auth_api.connector.entity.UserEntity;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    
    Optional<RefreshTokenEntity> findByToken(String token);
    
    Optional<RefreshTokenEntity> findByUser(UserEntity user);
    
    @Transactional
    void deleteByUser(UserEntity user);
    
    @Transactional
    void deleteByToken(String token);
    
}
