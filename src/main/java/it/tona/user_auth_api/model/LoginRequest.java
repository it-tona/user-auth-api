package it.tona.user_auth_api.model;



import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequest {

    @NonNull
    private String email;

    @NonNull
    private String password;
}