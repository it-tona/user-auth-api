package it.tona.user_auth_api.connector.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.tona.user_auth_api.connector.entity.UserEntity;
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

} 