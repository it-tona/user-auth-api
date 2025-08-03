package it.tona.user_auth_api.model;


import lombok.*;

@Data
public class RegisterRequest {

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String fullName;

}