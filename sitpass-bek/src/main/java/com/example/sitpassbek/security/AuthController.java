package com.example.sitpassbek.security;


import com.example.sitpassbek.dto.auth.AuthRequest;
import com.example.sitpassbek.dto.auth.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.signIn(authRequest);
        return ResponseEntity.ok(response);
    }


}