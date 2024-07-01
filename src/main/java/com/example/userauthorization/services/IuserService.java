package com.example.userauthorization.services;

import com.example.userauthorization.models.User;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.util.MultiValueMap;

public interface IuserService {
    User signUpService(String email , String password);

    public Pair<User,MultiValueMap<String,String>> loginService(String email , String password);


    boolean validateToken(String token, Long userId);
}
