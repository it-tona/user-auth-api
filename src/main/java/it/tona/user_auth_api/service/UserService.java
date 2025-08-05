package it.tona.user_auth_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tona.user_auth_api.connector.entity.UserEntity;
import it.tona.user_auth_api.connector.repository.UserRepository;
import it.tona.user_auth_api.mapper.UserMapper;
import it.tona.user_auth_api.model.User;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    /**
     * Finds a user by email.
     *
     * @param email the email of the user to find
     * @return 
     * @return the user if found, null otherwise
     */
    
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Saves a user to the repository.
     *
     * @param user the user to save
     */
    public void save(User user) {
        userRepository.save(UserMapper.toEntity(user));
    }

}
