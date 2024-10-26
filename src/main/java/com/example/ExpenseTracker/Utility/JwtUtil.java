package com.example.ExpenseTracker.Utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String  secretKey = "MySecretKeyIs245ForTheExpenseTrackerAPI";
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public String generateToken(String username){

        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(
                    System.currentTimeMillis() +
                            1000 * 60 * 60 * 12))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public String  extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean isTokenExpired(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }


    public boolean isTokenValid(String token,String username){
        return (username.equals(extractUsername(token)))
                && (!isTokenExpired(token));
    }
}
