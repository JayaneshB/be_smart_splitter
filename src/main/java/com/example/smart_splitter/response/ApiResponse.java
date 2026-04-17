package com.example.smart_splitter.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private int code;
    private String error;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .code(200)
                .error(null)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> failure(int code, String error) {
        return ApiResponse.<T>builder()
                .code(code)
                .error(error)
                .data(null)
                .build();
    }
}
