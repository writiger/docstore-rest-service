package com.ds.common.exception;

import lombok.Getter;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-09 19:17
 */
@Getter
public class CommonException extends RuntimeException{
    private int code;

    public CommonException(String message, int code) {
        super(message);
        this.code = code;
    }

    public CommonException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public CommonException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }
}
