package org.exam.common.exception.point;

import org.exam.common.exception.BaseException;
import org.exam.common.exception.ErrorType;

public class NoEnoughTodayPointException extends BaseException {

    public NoEnoughTodayPointException() {
        super(ErrorType.NO_ATTENDANCE_POINT_COUNT.getCode(),
                ErrorType.NO_ATTENDANCE_POINT_COUNT.getMessage(),
                ErrorType.NO_ATTENDANCE_POINT_COUNT.getHttpStatus());
    }
}
