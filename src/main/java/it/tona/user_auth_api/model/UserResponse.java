package it.tona.user_auth_api.model;

import lombok.Data;

@Data
public class UserResponse {

    private Integer id;
    private String email;
    private String fullName;
    private String role;

}