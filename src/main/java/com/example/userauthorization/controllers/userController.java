package com.example.userauthorization.controllers;

import com.example.userauthorization.Dtos.ForgotPasswordDto;
import com.example.userauthorization.Dtos.LoginRequestDto;
import com.example.userauthorization.Dtos.SignUpRequestDto;
import com.example.userauthorization.Dtos.UserDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    public UserDto signup(SignUpRequestDto signUpRequestDto)
    {

        return null;
    }

    public UserDto login(LoginRequestDto loginRequestDto)
    {
        return null;
    }

    public void logout()
    {
        return ;
    }

    public UserDto forgotPassword(ForgotPasswordDto forgotPasswordDto)
    {
        return null;
    }
}
