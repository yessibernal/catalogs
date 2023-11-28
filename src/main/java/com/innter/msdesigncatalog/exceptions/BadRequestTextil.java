package com.innter.msdesigncatalog.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BadRequestTextil extends RuntimeException{

    private String code;
    private HttpStatus status;

    public BadRequestTextil(String code, HttpStatus status, String message) {

        super(message);
        this.code = code;
        this.status = status;
    }
}
