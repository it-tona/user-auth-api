package it.tona.user_auth_api.model;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private String refreshToken;
}
