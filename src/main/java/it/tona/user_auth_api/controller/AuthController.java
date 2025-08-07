package it.tona.user_auth_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import it.tona.user_auth_api.api.UserAuthApi;
import it.tona.user_auth_api.config.BaseOut;
import it.tona.user_auth_api.logic.AuthLogic;
import it.tona.user_auth_api.model.ForgotRequest;
import it.tona.user_auth_api.model.LoginRequest;
import it.tona.user_auth_api.model.RefreshTokenRequest;
import it.tona.user_auth_api.model.RegisterRequest;
import it.tona.user_auth_api.model.ResetRequest;
import it.tona.user_auth_api.service.EmailService;
import it.tona.user_auth_api.service.PasswordResetService;

@RestController
public class AuthController implements UserAuthApi {

    @Autowired
    private AuthLogic authLogic;

    @Autowired private PasswordResetService passwordResetService;
    @Autowired private EmailService emailService;
    
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

    public ResponseEntity<? extends BaseOut> forgotPassword(ForgotRequest body) {
        String email = body.getEmail();
        String token = passwordResetService.createResetToken(email);
        emailService.sendResetPasswordEmail(email, token);

        BaseOut response = new BaseOut();
        response.setSuccessMessage("Email di reset inviata");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseOut> resetPassword(ResetRequest body, String token) {
        String newPassword = body.getPassword() == body.getConfirmPassword() ? body.getPassword() : null; 

        passwordResetService.resetPassword(token, newPassword);
        BaseOut response = new BaseOut();
        response.setSuccessMessage("Password aggiornata con successo");
        return ResponseEntity.ok(response);
    }
}
