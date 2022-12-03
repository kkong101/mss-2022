package org.exam.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Slf4j
public class BaseException extends RuntimeException{
    private final String code;

    private final String message;

    @JsonIgnore
    private final HttpStatus httpStatus;

    public BaseException(String errorCode, String message, HttpStatus httpStatus) {
        this.code = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
