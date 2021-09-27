package com.carlos.costura.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    private OffsetDateTime timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;

}
