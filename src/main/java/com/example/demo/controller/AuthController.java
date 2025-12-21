package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService service;

    public AuthController(UserAccountService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(
            @RequestBody RegisterRequest request) {

        UserAccount user = new UserAccount();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        return new ResponseEntity<>(
                service.register(user),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody AuthRequest request) {

        UserAccount user =
                service.findByEmail(request.getEmail());

        if (!user.getPassword()
                .equals(request.getPassword())) {
            throw new IllegalArgumentException(
                    "Invalid credentials");
        }

        return new AuthResponse(
                "dummy-jwt-token",
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
