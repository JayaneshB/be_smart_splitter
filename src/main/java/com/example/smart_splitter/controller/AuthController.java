package com.example.smart_splitter.controller;

import com.example.smart_splitter.dto.SignInRequest;
import com.example.smart_splitter.dto.SignUpRequest;
import com.example.smart_splitter.response.ApiResponse;
import com.example.smart_splitter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/smart-splitter")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> signup(@RequestBody SignUpRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        authService.signup(request)
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody SignInRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        authService.login(request)
                )
        );
    }

}
