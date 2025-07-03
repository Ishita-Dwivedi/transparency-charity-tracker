package com.transparency.controller;

import com.transparency.dto.AuthRequest;
import com.transparency.service.CustomUserDetailsService;
import com.transparency.security.JwtUtil;
import com.transparency.entity.User;
import com.transparency.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        // just for checking
        System.out.println("📝 Registering user: " + user.getEmail());
        System.out.println("🔐 Encoded password: " + user.getPassword());


        return ResponseEntity.ok("✅ User registered successfully!");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            System.out.println("🔐 Authenticating user: " + request.getEmail());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            System.out.println("✅ Auth successful");

        } catch (Exception ex) {
            System.out.println("❌ Authentication failed: " + ex.getMessage());
            ex.printStackTrace(); // Should show in console if configured
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        try {
            System.out.println("🔄 Loading user details...");
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

            System.out.println("🔐 Generating token...");
            String token = jwtUtil.generateToken(userDetails);

            System.out.println("🔍 Fetching user entity...");
            Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
            if (optionalUser.isEmpty()) {
                System.out.println("⚠️ User not found in DB.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            User user = optionalUser.get();
            System.out.println("👤 Role: " + user.getRole());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", user.getRole().name());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("💥 Post-auth error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed due to server error");
        }
    }



}

