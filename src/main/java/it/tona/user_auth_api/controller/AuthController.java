package it.tona.user_auth_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import it.tona.user_auth_api.api.UserAuthApi;
import it.tona.user_auth_api.config.BaseOut;
import it.tona.user_auth_api.logic.AuthLogic;
import it.tona.user_auth_api.model.LoginRequest;
import it.tona.user_auth_api.model.RefreshTokenRequest;
import it.tona.user_auth_api.model.RegisterRequest;

@RestController
public class AuthController implements UserAuthApi {

    @Autowired
    private AuthLogic authLogic;
    
    public ResponseEntity<? extends BaseOut> registerUser(RegisterRequest registerRequest){
        return authLogic.execute(registerRequest);
    }

    
    public ResponseEntity<? extends BaseOut> loginUser(LoginRequest loginRequest){
        return authLogic.execute(loginRequest);
    }


    public ResponseEntity<? extends BaseOut> refreshToken(RefreshTokenRequest request) {
        String requestToken = request.getRefreshToken();

        return authLogic.execute(requestToken);
    }


    @Override
    public ResponseEntity<? extends BaseOut> forgotPassword(LoginRequest loginRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'forgotPassword'");
    }
}
