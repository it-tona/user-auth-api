package it.tona.user_auth_api.model;

import it.tona.user_auth_api.config.BaseOut;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends BaseOut{

    private Integer id;
    private String email;
    private String fullName;
    private String role;

}