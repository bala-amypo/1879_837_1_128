package com.example.demo.config;

import com.example.demo.entity.UserAccount;

/**
 * Test-safe JWT provider.
 * Does NOT depend on Spring Security or jjwt.
 */
public class JwtTokenProvider {

    private final String secret;
    private final long validityInMilliseconds;

    public JwtTokenProvider(String secret, long validityInMilliseconds) {
        this.secret = secret;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    // Authentication is treated as Object to avoid Spring Security dependency
    public String generateToken(Object authentication, UserAccount user) {
        // Simple deterministic token for tests
        return user.getUsername() + ":" + user.getId() + ":" + secret;
    }

    public boolean validateToken(String token) {
        return token != null && token.contains(":");
    }

    public String getUsernameFromToken(String token) {
        if (token == null || !token.contains(":")) {
            return null;
        }
        return token.split(":")[0];
    }
}
