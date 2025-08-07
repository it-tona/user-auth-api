package it.tona.user_auth_api.model;

import lombok.Data;

@Data
public class ForgotRequest {
    private String email;
    private String fullName;
}
