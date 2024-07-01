package com.example.userauthorization.models;
import com.example.userauthorization.enums.SessionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter

@Getter

@Entity
public class Session extends baseModel{

    @ManyToOne
    private User user;

    private String token;

    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;


}
