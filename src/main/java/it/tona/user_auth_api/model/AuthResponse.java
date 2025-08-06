package it.tona.user_auth_api.model;

import it.tona.user_auth_api.config.BaseOut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AuthResponse extends BaseOut{

    private String token;
    private String refreshToken;

}