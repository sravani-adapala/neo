package com.neoteric.fullstackdemo.exception;

public class AccountCreationFailedException extends Exception {
    public String message;

    public AccountCreationFailedException(String message){
        this.message=message;
    }
}
