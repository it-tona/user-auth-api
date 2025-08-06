package it.tona.user_auth_api.config;

import lombok.Data;

@Data
public class BaseOut {
    String errorCode;
    String errorMessage;
}
