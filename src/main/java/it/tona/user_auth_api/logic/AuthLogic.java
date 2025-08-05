package it.tona.user_auth_api.logic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.tona.user_auth_api.config.BaseServiceAndLogic;
import it.tona.user_auth_api.connector.entity.UserEntity;
import it.tona.user_auth_api.mapper.UserMapper;
import it.tona.user_auth_api.model.AuthResponse;
import it.tona.user_auth_api.model.LoginRequest;
import it.tona.user_auth_api.model.RegisterRequest;
import it.tona.user_auth_api.model.User;
import it.tona.user_auth_api.model.enumeration.Role;
import it.tona.user_auth_api.service.JwtService;
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
    
    public ResponseEntity<AuthResponse> execute(RegisterRequest registerRequest) {
        log.info("Registering user with email: {}", registerRequest.getEmail());

        if (userService.findByEmail(registerRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new AuthResponse("Email already registered"));
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullName());
        user.setRole(Role.USER);

        userService.save(user);

        return ResponseEntity.ok(new AuthResponse(jwtService.generateToken(user.getEmail())));
        
    }

    public ResponseEntity<AuthResponse> execute(LoginRequest loginRequest) {
        log.info("Logging in user with email: {}", loginRequest.getEmail());
        
        Optional<UserEntity> userEntity = userService.findByEmail(loginRequest.getEmail());
        if (userEntity.isEmpty()) {
            return ResponseEntity.status(401).body(new AuthResponse("Invalid email or password"));
        }
        
        User user = UserMapper.toModel(userEntity.get());
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(new AuthResponse("Invalid email or password"));
        }

        return ResponseEntity.ok(new AuthResponse(jwtService.generateToken(user.getEmail())));
        
    }

}
