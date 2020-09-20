package com.pycogroup.pitsa.security;


import com.pycogroup.pitsa.model.User;
import io.jsonwebtoken.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

/**
 * Utility Class for common Java Web Token operations
 */
@Component
public class JwtProvider {
    private JwtParser parser;

    private String secretKey;

    private String ROLE_KEY = "roles";
    @Getter
    private String token;

    private long validityInMilliseconds;

    @Autowired
    public  JwtProvider(@Value("${security.jwt.token.secret-key}") String secretKey,
                        @Value("${security.jwt.token.expiration}")long validityInMilliseconds){
        this.secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }

    /**
     * Create JWT string given username, phone, address.
     *
     * @param user  : the principal
     * @return jwt string
     */

    public String createToken(User user) {

        Claims claims = Jwts.claims().setSubject(user.getFirstname());
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + validityInMilliseconds);

        token = Jwts.builder()
                .setClaims(claims)
                .claim("phone",user.getPhone())
                .claim("email",user.getEmail())
                .claim("password", user.getPassword())
                .claim("id",user.get_id())
                .setIssuedAt(now)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return token;

    }

    /**
     * Validate the JWT String
     *
     * @param token JWT string
     * @return true if valid, false otherwise
     */
    public boolean isValidToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    /**
     * Get the username from the token string
     *
     * @param token jwt
     * @return firstName
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }
}
