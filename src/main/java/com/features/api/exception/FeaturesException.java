package com.features.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FeaturesException extends Exception{
    private HttpStatus errorCode;

    public FeaturesException(HttpStatus errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
