package org.exam.common.exception;


import lombok.extern.slf4j.Slf4j;
import org.exam.common.exception.point.AlreadyGotTodayPointException;
import org.exam.common.exception.point.NoEnoughTodayPointException;
import org.exam.common.exception.user.NoExistUserException;
import org.exam.controller.dto.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            NoExistUserException.class, NoEnoughTodayPointException.class, AlreadyGotTodayPointException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse serviceException(HttpServletResponse response, BaseException e) {

        log.debug("SERVICE EXCEPTION", e);

        response.setStatus(e.getHttpStatus().value());
        return CustomResponse.ok(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public CustomResponse runtimeError(HttpServletResponse response, Exception e) {

        log.error("RUNTIME ERROR", e);

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return CustomResponse.ok("9999", e.getMessage());
    }

}
