package com.example.userauthorization.Dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
