package com.example.POS.System.security;

import com.example.POS.System.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTGenerator {

    @Value("${spring.app.JWTExpiration}")
    private Long JWTExpiration;

    @Value("${spring.app.JWTSecret}")
    private String jwtSecret;

    public String generateToken(User auth) {
        String username = "" + auth.getId();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + JWTExpiration);

        Key signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());  // Ensure the key length is appropriate

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(signingKey, SignatureAlgorithm.HS512)  // Updated to use Key object
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build()                              // Build the parser
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Invalid token");
        }
    }
}
