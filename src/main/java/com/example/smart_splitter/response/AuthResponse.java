package com.example.smart_splitter.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthResponse {
    public String accessToken;
    public String refreshToken;
    public String description;
    public UUID uuid;
}
