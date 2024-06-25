package com.example.userauthorization.models;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User extends baseModel {
    private String name ;
    private String email ;
    private String password ;
}
