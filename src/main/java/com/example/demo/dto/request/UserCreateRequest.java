package com.example.demo.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserCreateRequest {
    private String username;
    private String password;
    private LocalDate dbo;
}
