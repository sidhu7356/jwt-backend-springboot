package com.sid.jwtbackend.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "styrdcjgvkhtgfhvhhfggvvhvggvvhvvhvhvhvhvhvh";
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    private Key getSigningKey() {
        // Implement the logic to create and return a signing key using the SECRET
        // This could involve using a library like JJWT or similar to generate a Key object
        return Keys.hmacShaKeyFor(SECRET.getBytes()); // Placeholder, replace with actual key generation logic
    }

    public String generateToken(String username) {
        // Implement the logic to generate a JWT token using the signing key and username
        // This could involve using a library like JJWT to create the token
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact(); // Placeholder, replace with actual token generation logic
    }

    public String extractUsername(String token) {
        // Implement the logic to extract the username from the JWT token
        // This could involve parsing the token and retrieving the subject
        return parseToken(token).getBody().getSubject(); // Placeholder, replace with actual extraction logic
    }

    public boolean isTokenValid(String token, String username) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }

    private Jws<Claims> parseToken(String token) {
        // Implement the logic to parse the JWT token and return its claims
        // This could involve using a library like JJWT to parse the token
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token); // Placeholder, replace with actual parsing logic
    }
}
