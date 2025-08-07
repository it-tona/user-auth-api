package it.tona.user_auth_api.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.tona.user_auth_api.connector.entity.PasswordResetTokenEntity;
import it.tona.user_auth_api.connector.entity.UserEntity;
import it.tona.user_auth_api.connector.repository.*;

@Service
public class PasswordResetService {

    @Autowired private PasswordResetTokenRepository tokenRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    public String createResetToken(String email) {
        UserEntity user = userRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Rimuovi token precedenti
        tokenRepo.deleteByUser(user);

        String token = UUID.randomUUID().toString();

        PasswordResetTokenEntity entity = new PasswordResetTokenEntity();
        entity.setToken(token);
        entity.setUser(user);
        entity.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        tokenRepo.save(entity);
        return token;
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetTokenEntity resetToken = tokenRepo.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Token non valido"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token scaduto");
        }

        UserEntity user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);

        tokenRepo.delete(resetToken);
    }
}
