package it.tona.user_auth_api.api;

import it.tona.user_auth_api.model.RegisterRequest;
import it.tona.user_auth_api.model.LoginRequest;
import it.tona.user_auth_api.model.AuthResponse;
import it.tona.user_auth_api.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public interface UserAuthApi {

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody RegisterRequest registerRequest);

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest);

    @GetMapping("/users/me")
    public ResponseEntity<UserResponse> getCurrentUser();
}