package com.example.security;

import com.example.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil
{
    private final String SECRET = "mysecretkey";

    public String generateToken(String email, Role role)
    {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60))
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }

    public String extractEmail(String token)
    {
    return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token)
    {
    return extractClaims(token).getExpiration().after(new Date());
    }

    private Claims extractClaims(String token)
    {
    return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

}
