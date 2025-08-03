package it.tona.user_auth_api.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public interface UserAuthApi {

    @PostMapping("/register")
    ResponseEntity<Void> registerUser(@RequestBody UserDto userDto);

    @PostMapping("/login")
    ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest);

    @GetMapping("/users/{id}")
    ResponseEntity<UserDto> getCurrentUser();
}