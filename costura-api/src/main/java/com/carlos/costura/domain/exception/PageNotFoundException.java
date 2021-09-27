package com.carlos.costura.domain.exception;

import lombok.Getter;

@Getter
public class PageNotFoundException extends RuntimeException{

    private String message;

    public PageNotFoundException(String message) {
        this.message = message;
    }
}
