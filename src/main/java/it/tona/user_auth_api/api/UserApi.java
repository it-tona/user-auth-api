package it.tona.user_auth_api.api;


import it.tona.user_auth_api.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
public interface UserApi {

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser();
}