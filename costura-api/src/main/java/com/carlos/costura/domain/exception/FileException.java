package com.carlos.costura.domain.exception;

import lombok.Getter;

@Getter
public class FileException extends RuntimeException{


    public FileException(String message) {
        super(message);
    }

    public FileException(String msg, Throwable cause){
        super(msg,cause);
    }
}
