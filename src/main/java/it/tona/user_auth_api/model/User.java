package it.tona.user_auth_api.model;


import it.tona.user_auth_api.model.enumeration.Role;
import lombok.Data;

@Data
public class User {
    private String email;
    private String password;
    private String fullName;
    private Role role = Role.USER;
}