package it.tona.user_auth_api.api;

import it.tona.user_auth_api.model.RegisterRequest;
import it.tona.user_auth_api.model.LoginRequest;
import it.tona.user_auth_api.model.RefreshTokenRequest;
import it.tona.user_auth_api.config.BaseOut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public interface UserAuthApi {

    @PostMapping("/register")
    public ResponseEntity<? extends BaseOut> registerUser(@RequestBody RegisterRequest registerRequest);

    @PostMapping("/login")
    public ResponseEntity<? extends BaseOut> loginUser(@RequestBody LoginRequest loginRequest);

    @PostMapping("/refresh-token")
    public ResponseEntity<? extends BaseOut> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest);

    @PostMapping("/forgot-password")
    public ResponseEntity<? extends BaseOut> forgotPassword(@RequestBody LoginRequest loginRequest);
}