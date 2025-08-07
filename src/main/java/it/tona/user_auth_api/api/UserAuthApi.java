package it.tona.user_auth_api.api;

import it.tona.user_auth_api.model.RegisterRequest;
import it.tona.user_auth_api.model.AuthResponse;
import it.tona.user_auth_api.model.LoginRequest;
import it.tona.user_auth_api.model.RefreshTokenRequest;
import it.tona.user_auth_api.config.BaseOut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/api/auth")
public interface UserAuthApi {

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "User registered successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(oneOf = { AuthResponse.class })
        )),
    @ApiResponse(responseCode = "400", description = "Email already registered",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = BaseOut.class)
        ))
    })
    @PostMapping("/register")
    public ResponseEntity<? extends BaseOut> registerUser(@RequestBody RegisterRequest registerRequest);

    @Operation(summary = "Login user")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Login success",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(oneOf = { AuthResponse.class })
        )),
    @ApiResponse(responseCode = "400", description = "Invalid credentials",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = BaseOut.class)
        ))
    })
    @PostMapping("/login")
    public ResponseEntity<? extends BaseOut> loginUser(@RequestBody LoginRequest loginRequest);


    @Operation(summary = "Refresh user token")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Token refreshed successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(oneOf = { AuthResponse.class })
        )),
    @ApiResponse(responseCode = "400", description = "Invalid refresh token",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = BaseOut.class)
        ))
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<? extends BaseOut> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest);


    @Operation(summary = "Forgot password")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Password reset link sent",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(oneOf = { AuthResponse.class })
        )),
    @ApiResponse(responseCode = "400", description = "Invalid email",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = BaseOut.class)
        ))
    })
    @PostMapping("/forgot-password")
    public ResponseEntity<? extends BaseOut> forgotPassword(@RequestBody LoginRequest loginRequest,
                                                            @RequestParam String token);


    @Operation(summary = "Reset password")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Password reset successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(oneOf = { AuthResponse.class })
        )),
    @ApiResponse(responseCode = "400", description = "Invalid email",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = BaseOut.class)
        ))
    })
    @PostMapping("/reset-password")
    public ResponseEntity<? extends BaseOut> resetPassword(@RequestBody LoginRequest loginRequest);
}