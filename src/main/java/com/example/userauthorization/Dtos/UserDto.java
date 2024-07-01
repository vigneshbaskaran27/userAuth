package com.example.userauthorization.Dtos;

import com.example.userauthorization.models.Role;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class UserDto{

    private String email;
    private List<Role> roles;
}
