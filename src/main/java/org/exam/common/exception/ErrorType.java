package org.exam.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    // USER
    NO_EXIST_USER("U001", "존재하지 않는 사용자.", HttpStatus.BAD_REQUEST),

    // POINT
    NO_ATTENDANCE_POINT_COUNT("P001", "오늘의 출석이 마감되었습니다.", HttpStatus.OK),
    ALREADY_GOT_TODAY_POINT("P002", "이미 오늘 출석을 하였습니다.", HttpStatus.OK)
    ;

    private String code;
    private String message;
    private HttpStatus httpStatus;

}
