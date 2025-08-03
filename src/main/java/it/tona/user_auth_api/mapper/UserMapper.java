package it.tona.user_auth_api.mapper;

import it.tona.user_auth_api.connector.entity.UserEntity;
import it.tona.user_auth_api.model.User;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setFullName(user.getFullName());
        entity.setRole(user.getRole());
        return entity;
    }

    public static User toModel(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        User user = new User();
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setFullName(entity.getFullName());
        user.setRole(entity.getRole());
        return user;
    }
}
