package com.example.userauthorization.controllers;

import com.example.userauthorization.Dtos.*;

import com.example.userauthorization.exceptions.InValidTokenException;
import com.example.userauthorization.models.User;
import com.example.userauthorization.repository.SessionRepository;
import com.example.userauthorization.repository.UserRepository;
import com.example.userauthorization.services.IuserService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/users")
public class userController {

    @Autowired
    IuserService userService;
    @Autowired
    private LoginRequestDto loginRequestDto;
    @Autowired
    private User user;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        //on receiving check whether the user exist in db

        User user =userService.signUpService(signUpRequestDto.getEmail() , signUpRequestDto.getPassword());
        UserDto userDto = getUserDto(user);
        return userDto;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto)
    {
        try {
            Pair<User, MultiValueMap<String, String>> response = userService.loginService(loginRequestDto.getEmail() , loginRequestDto.getPassword());
            if(response==null)
            {
                throw new IllegalArgumentException("Invalid Credentials");
            }
            UserDto userDto = getUserDto(response.a);
            return new ResponseEntity<UserDto>(userDto,response.b, HttpStatus.OK);
        }catch(IllegalArgumentException ex)
        {
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }




    }

    @PostMapping("/logout")
    public void logout()
    {
        return ;
    }

    @PostMapping("/forgotPassword")
    public void forgotPassword( @RequestBody  ForgotPasswordDto forgotPasswordDto)
    {
        return ;
    }

    @PostMapping("/deleteUser")
    public void deleteUser(@RequestBody DeleteUserRequestDto deleteUsrRequestDto)
    {
        return;
    }

    @PostMapping("/validateToken")
    public void validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) throws InValidTokenException {
        boolean isValidated =userService.validateToken(validateTokenRequestDto.getToken() , validateTokenRequestDto.getUserId());
        if(!isValidated)
        {
            throw new InValidTokenException("Token not valid");
        }
        return;
    }

    private UserDto getUserDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
