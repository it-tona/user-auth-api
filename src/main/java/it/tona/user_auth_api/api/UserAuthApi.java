package it.tona.user_auth_api.api;

import it.tona.user_auth_api.model.RegisterRequest;
import it.tona.user_auth_api.model.LoginRequest;
import it.tona.user_auth_api.model.RefreshTokenRequest;
import it.tona.user_auth_api.model.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public interface UserAuthApi {

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest);

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest);

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest);

    @PostMapping("/forgot-password")
    public ResponseEntity<AuthResponse> forgotPassword(@RequestBody LoginRequest loginRequest);
}