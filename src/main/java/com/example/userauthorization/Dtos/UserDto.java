package com.example.userauthorization.Dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDto{
    private String name;
    private String email;
}
