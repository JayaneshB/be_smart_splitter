package com.example.smart_splitter.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {

    private final Key SECRET_KEY = Keys.hmacShaKeyFor(
            "my-super-secret-key-that-is-at-least-32-bytes!".getBytes()
    );

    public String generateToken(UUID userId, String email) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId.toString())
                .claim("type", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String generateRefreshToken(UUID userId, String email) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId.toString())
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 86400000))
                .signWith(SECRET_KEY)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}