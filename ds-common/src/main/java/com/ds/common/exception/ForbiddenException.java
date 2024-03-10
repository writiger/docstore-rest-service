package com.ds.common.exception;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-10 20:23
 */
public class ForbiddenException extends CommonException{

    public ForbiddenException(String message) {
        super(message, 403);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause, 403);
    }

    public ForbiddenException(Throwable cause) {
        super(cause, 403);
    }
}
