package com.example.sitpassbek.security;


import com.example.sitpassbek.dto.auth.AuthRequest;
import com.example.sitpassbek.dto.auth.AuthResponse;
import com.example.sitpassbek.model.User;
import com.example.sitpassbek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse signIn(AuthRequest authRequest) {
        AuthResponse response = new AuthResponse();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
            String jwt = jwtUtils.generateToken(user);

            response.setToken(jwt);
            response.setMessage("Successfully signed in");
        } catch (Exception e) {
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }

}