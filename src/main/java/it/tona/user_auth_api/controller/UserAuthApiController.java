package it.tona.user_auth_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import it.tona.user_auth_api.api.UserAuthApi;
import it.tona.user_auth_api.model.AuthResponse;
import it.tona.user_auth_api.model.LoginRequest;
import it.tona.user_auth_api.model.RegisterRequest;
import it.tona.user_auth_api.model.UserResponse;

@RestController
public class UserAuthApiController implements UserAuthApi {
    
    public ResponseEntity<Void> registerUser(RegisterRequest registerRequest){
        return ResponseEntity.ok().build();
    }

    
    public ResponseEntity<AuthResponse> loginUser(LoginRequest loginRequest){
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<UserResponse> getCurrentUser(){
        return ResponseEntity.ok().build();
    }
}
