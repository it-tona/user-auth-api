package it.tona.user_auth_api.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.tona.user_auth_api.config.BaseServiceAndLogic;
import it.tona.user_auth_api.connector.entity.RefreshTokenEntity;
import it.tona.user_auth_api.connector.entity.UserEntity;
import it.tona.user_auth_api.connector.repository.RefreshTokenRepository;
import it.tona.user_auth_api.connector.repository.UserRepository;


@Service
public class RefreshTokenService extends BaseServiceAndLogic {
    @Value("${app.jwt.refreshExpirationSec}")
    private Long refreshTokenDurationSec;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;
    /**
     * Crea un nuovo token di refresh per l'utente specificato.
     *
     * @param userId ID dell'utente per cui creare il token di refresh.
     * @return Il nuovo token di refresh creato.
     */
    public RefreshTokenEntity createRefreshToken(Long userId) {
        RefreshTokenEntity token = new RefreshTokenEntity();
        token.setUser(userRepository.findById(userId).get());
        token.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenDurationSec));
        token.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(token);
    }

    /**
     * Aggiorna il token di refresh esistente.
     *
     * @param refreshToken Il token di refresh da aggiornare.
     * @return Il token di refresh aggiornato.
     */
    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token scaduto.");
        }
        return token;
    }

    public Optional<RefreshTokenEntity> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public void deleteByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        refreshTokenRepository.deleteByUser(user);
    }
}
