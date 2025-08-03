package it.tona.user_auth_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import it.tona.user_auth_api.api.UserApi;
import it.tona.user_auth_api.model.User;
import it.tona.user_auth_api.model.UserResponse;

@RestController
public class UserController implements UserApi {


    public ResponseEntity<UserResponse> getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();
                        
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setFullName(user.getFullName());
        userResponse.setRole(user.getRole().name());

        return ResponseEntity.ok(userResponse);
    }

}
