package com.example.userauthorization.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {
    private String token;
    private Long UserId;

}
