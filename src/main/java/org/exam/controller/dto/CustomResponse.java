package org.exam.controller.dto;

import lombok.Getter;


@Getter
public class CustomResponse {

    private String code = "0000";

    private String message = "성공";

    public CustomResponse() {}

    public CustomResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CustomResponse ok(String code, String message){
        return new CustomResponse(code, message);
    }

    public static CustomResponse ok(Object body){
        return new Body<>(body);
    }

    public static CustomResponse ok(String code, String message, Object body){
        return new Body<>(code, message, body);
    }

    public static class Body<T> extends CustomResponse{

        @Getter
        private final T result;

        public Body(T result) {
            this.result = result;
        }

        public Body(String code, String message, T result) {
            super(code, message);
            this.result = result;
        }
    }

}
