package com.example.smart_splitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequest {
    public String emailId;
    public String password;
    public String name;
}
