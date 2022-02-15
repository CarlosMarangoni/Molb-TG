package com.carlos.costura.domain.exception;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException{

    private String message;

    public ConflictException(String message) {
        this.message = message;
    }
}
