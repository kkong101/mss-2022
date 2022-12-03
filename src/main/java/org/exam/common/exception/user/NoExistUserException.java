package org.exam.common.exception.user;

import lombok.extern.slf4j.Slf4j;
import org.exam.common.exception.BaseException;
import org.exam.common.exception.ErrorType;

public class NoExistUserException extends BaseException{

    public NoExistUserException() {
        super(ErrorType.NO_EXIST_USER.getCode(),
                ErrorType.NO_EXIST_USER.getMessage(),
                ErrorType.NO_EXIST_USER.getHttpStatus());
    }
}
