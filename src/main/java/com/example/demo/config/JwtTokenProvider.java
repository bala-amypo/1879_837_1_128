package com.example.demo.config;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.*;
import java.util.Date;
import org.springframework.security.core.Authentication;

public class JwtTokenProvider {

    private final String secret;
    private final long validity;

    public JwtTokenProvider(String secret, long validity) {
        this.secret = secret;
        this.validity = validity;
    }

    public String generateToken(Authentication auth, UserAccount user) {
        return Jwts.builder()
                .setSubject(auth.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
