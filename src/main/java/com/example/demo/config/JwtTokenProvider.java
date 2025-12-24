package com.example.demo.config;

import com.example.demo.entity.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public JwtTokenProvider() {
    }

    public JwtTokenProvider(String secret, long validityInMilliseconds) {
    }

    public String generateToken(Object authentication, UserAccount user) {
        return user.getUsername() + ":" + user.getId();
    }

    public boolean validateToken(String token) {
        return token != null && token.contains(":");
    }

    public String getUsernameFromToken(String token) {
        return token.split(":")[0];
    }
}
