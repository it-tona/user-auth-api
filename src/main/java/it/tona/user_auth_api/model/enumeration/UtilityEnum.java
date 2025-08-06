package it.tona.user_auth_api.model.enumeration;

public enum UtilityEnum {
    EMAIL_ALREADY_REGISTERED("Email already registered"),
    INVALID_CREDENTIALS("Invalid credentials"),
    USER_NOT_FOUND("User not found");

    private String value;

    UtilityEnum(String value) {
        this.value = value;
        
    }
    public String getValue() {
        return value;
    }

}   
