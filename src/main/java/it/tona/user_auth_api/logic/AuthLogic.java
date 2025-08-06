package it.tona.user_auth_api.logic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.tona.user_auth_api.config.BaseOut;
import it.tona.user_auth_api.config.BaseServiceAndLogic;
import it.tona.user_auth_api.connector.entity.RefreshTokenEntity;
import it.tona.user_auth_api.connector.entity.UserEntity;
import it.tona.user_auth_api.mapper.UserMapper;
import it.tona.user_auth_api.model.AuthResponse;
import it.tona.user_auth_api.model.LoginRequest;
import it.tona.user_auth_api.model.RegisterRequest;
import it.tona.user_auth_api.model.User;
import it.tona.user_auth_api.model.enumeration.Role;
import it.tona.user_auth_api.model.enumeration.UtilityEnum;
import it.tona.user_auth_api.service.JwtService;
import it.tona.user_auth_api.service.RefreshTokenService;
import it.tona.user_auth_api.service.UserService;
import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class AuthLogic extends BaseServiceAndLogic {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RefreshTokenService refreshTokenService;
    
    public ResponseEntity<? extends BaseOut> execute(RegisterRequest registerRequest) {
        log.info("Registering user with email: {}", registerRequest.getEmail());

        AuthResponse response = new AuthResponse();

        if (userService.findByEmail(registerRequest.getEmail()).isPresent()) {
            BaseOut baseOut = new BaseOut();
            baseOut.setErrorMessage(UtilityEnum.EMAIL_ALREADY_REGISTERED.getValue());
            return ResponseEntity.badRequest().body(baseOut);
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullName());
        user.setRole(Role.USER);

        userService.save(user);

        response.setToken(jwtService.generateToken(user.getEmail()));

        return ResponseEntity.ok(response);
        
    }

    public ResponseEntity<? extends BaseOut> execute(LoginRequest loginRequest) {
        log.info("Logging in user with email: {}", loginRequest.getEmail());

        AuthResponse response = new AuthResponse();
        
        Optional<UserEntity> userEntity = userService.findByEmail(loginRequest.getEmail());
        if (userEntity.isEmpty()) {
            BaseOut baseOut = new BaseOut();
            baseOut.setErrorMessage(UtilityEnum.INVALID_CREDENTIALS.getValue());
            return ResponseEntity.status(401).body(baseOut);
        }
        
        User user = UserMapper.toModel(userEntity.get());
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            BaseOut baseOut = new BaseOut();
            baseOut.setErrorMessage(UtilityEnum.INVALID_CREDENTIALS.getValue());
            return ResponseEntity.status(401).body(baseOut);
        }

        response.setToken(jwtService.generateToken(user.getEmail()));
        
        return ResponseEntity.ok(response);
        
    }


    public ResponseEntity<? extends BaseOut> execute(String requestToken) {
        return refreshTokenService.findByToken(requestToken)
            .map(refreshTokenService::verifyExpiration)
            .map(RefreshTokenEntity::getUser)
            .map(user -> {
                String newAccessToken = jwtService.generateToken(user.getEmail());
                String newRefreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();

                return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken));
            })
            .orElseThrow(() -> new RuntimeException("Refresh token non valido"));
        
    }
}
