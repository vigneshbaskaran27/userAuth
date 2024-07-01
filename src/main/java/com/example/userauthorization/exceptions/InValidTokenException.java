package com.example.userauthorization.exceptions;

import org.aspectj.bridge.IMessage;

public class InValidTokenException extends Exception{
    public InValidTokenException(String message){
        super(message);
    }
}
