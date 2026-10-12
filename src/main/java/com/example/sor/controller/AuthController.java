
package com.example.sor.controller;

import com.example.sor.dto.LoginRequest;
import com.example.sor.dto.LoginResponse;
import com.example.sor.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Authentication",
        description = "APIs for user registration and login"
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Register a user",
            description = "Creates a user account with an encoded password"
    )
    public String register(@RequestBody LoginRequest request) {
        authService.register(
                request.getUsername(),
                request.getPassword()
        );

        return "User registered successfully";
    }

    @PostMapping("/login")
    @Operation(
            summary = "Log in",
            description = "Authenticates a user and returns a JWT token"
    )
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
