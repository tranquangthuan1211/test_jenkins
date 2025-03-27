package com.example.demo.exception;

import com.example.demo.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimException(RuntimeException exception){
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code(ErrorCode.USER_EXISTED.getCode())
                .message(ErrorCode.USER_NOT_EXITS.getMessage())
                .build();

        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse response = ApiResponse.<String>builder()
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .build();

        return ResponseEntity.badRequest().body(response);
    }
}
