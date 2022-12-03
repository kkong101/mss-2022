package org.exam.common.exception.point;


import org.exam.common.exception.BaseException;
import org.exam.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public class AlreadyGotTodayPointException extends BaseException {

    public AlreadyGotTodayPointException() {
        super(ErrorType.ALREADY_GOT_TODAY_POINT.getCode(),
                ErrorType.ALREADY_GOT_TODAY_POINT.getMessage(),
                ErrorType.ALREADY_GOT_TODAY_POINT.getHttpStatus());
    }
}
