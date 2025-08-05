package it.tona.user_auth_api.api;


import it.tona.user_auth_api.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RequestMapping("/api/users")
public interface UserApi {

    @Operation(
        summary = "Restituisce l'utente attualmente autenticato",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser();
}