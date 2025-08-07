package it.tona.user_auth_api.model;

import lombok.Data;

@Data
public class ResetRequest {
    private String password;
    private String confirmPassword;
}
