package com.ds.common.exception;


/**
 * @author writiger
 * @description
 * @create_at 2024-03-08 19:18
 */
public class BadRequestException extends CommonException{

    public BadRequestException(String message) {
        super(message, 400);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause, 400);
    }

    public BadRequestException(Throwable cause) {
        super(cause, 400);
    }
}
