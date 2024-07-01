package com.example.userauthorization.Dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
@Setter
public class SignUpRequestDto {

    private String email;
    private String password;
}
