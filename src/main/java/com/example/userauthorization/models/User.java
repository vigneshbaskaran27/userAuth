package com.example.userauthorization.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Component
@Getter
@Setter
public class User extends baseModel {

    private String email ;
    private String password ;
    @ManyToMany
    List<Role> roles=new ArrayList<>();
}
