package it.tona.user_auth_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import it.tona.user_auth_api.api.UserAuthApi;
import it.tona.user_auth_api.logic.AuthLogic;
import it.tona.user_auth_api.model.AuthResponse;
import it.tona.user_auth_api.model.LoginRequest;
import it.tona.user_auth_api.model.RefreshTokenRequest;
import it.tona.user_auth_api.model.RegisterRequest;

@RestController
public class AuthController implements UserAuthApi {

    @Autowired
    private AuthLogic authLogic;
    
    public ResponseEntity<AuthResponse> registerUser(RegisterRequest registerRequest){
        return authLogic.execute(registerRequest);
    }

    
    public ResponseEntity<AuthResponse> loginUser(LoginRequest loginRequest){
        return authLogic.execute(loginRequest);
    }


    public ResponseEntity<AuthResponse> refreshToken(RefreshTokenRequest request) {
        String requestToken = request.getRefreshToken();

        return authLogic.execute(requestToken);
    }


    @Override
    public ResponseEntity<AuthResponse> forgotPassword(LoginRequest loginRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'forgotPassword'");
    }
}
