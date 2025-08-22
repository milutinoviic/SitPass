package com.example.sitpassbek.security;


import com.example.sitpassbek.model.Administrator;
import com.example.sitpassbek.model.User;
import com.example.sitpassbek.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;


import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {

    private final UserRepository userRepository;
    private SecretKey Key;

    private static final long EXPIRATION_TIME = 86400000;

    public JwtUtils(UserRepository userRepository) {
        String secreteString = "1234567890098765432134235353453928573253793279572957979257959759785978593785978399879378fihhieihvihrviherreivhivhivhiehivehi";
        byte[] keyBytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
        this.Key = new SecretKeySpec(keyBytes, "HmacSHA256");
        this.userRepository = userRepository;
    }

    public String generateToken(UserDetails userDetails) {
        Optional<User> user = userRepository.findByEmail((userDetails.getUsername()));
        String role = (user.get() instanceof Administrator) ? "Admin" : "User";
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("id", user.get().getId())
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }


    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(Key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}