package com.example.smart_splitter.service;


import com.example.smart_splitter.dto.SignInRequest;
import com.example.smart_splitter.dto.SignUpRequest;
import com.example.smart_splitter.entity.User;
import com.example.smart_splitter.repository.AuthRepository;
import com.example.smart_splitter.response.AuthResponse;
import com.example.smart_splitter.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Transactional
    public AuthResponse signup(SignUpRequest request) {

        if (authRepository.existsByEmail(request.getEmailId()))
            throw new RuntimeException("Email already registered");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(request.getName());
        user.setEmail(request.getEmailId());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        authRepository.save(user);

        String token = jwtUtils.generateToken(user.getId(), user.getEmail());

        String refreshToken = jwtUtils.generateRefreshToken(user.getId(), user.getEmail());

        return new AuthResponse(
                token,
                refreshToken,
                "User registered successfully",
                user.getId()
        );
    }


    public AuthResponse login(SignInRequest request) {

        User user = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        String token = jwtUtils.generateToken(user.getId(), user.getEmail());

        String refreshToken = jwtUtils.generateRefreshToken(user.getId(), user.getEmail());

        return new AuthResponse(
                token,
                refreshToken,
                "User logged-in successfully",
                user.getId()
        );
    }
}
