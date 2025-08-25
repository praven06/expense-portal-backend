package com.praveen.expportal.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import org.springframework.stereotype.Service;
import com.praveen.expportal.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private static final long EXPIRATION_TIME = 86400000; 
    String secretKey = "my-32-byte-secret-key-jerry-tom-praveen-ig-it-is-not-a-secret-anymore-they-need-more-it-seems";
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
    
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("empId", user.getEmployeeId());
        
        return Jwts.builder()
        .setClaims(claims)
        .setSubject(user.getEmployeeId())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
       
    }
    
    
    public boolean validateToken(String token) {
        System.out.println(token);
        try {
            if (token == null || token.isEmpty()) {
                return false;
            }
            else {
                return true;
            }
        } catch (JwtException e) {
            return false; 
        }
    }
}
