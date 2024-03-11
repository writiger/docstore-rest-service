package com.ds.common.exception;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-11 13:51
 */
public class AccountIsEmptyException extends CommonException{
    public AccountIsEmptyException(String message) {
        super(message, 416);
    }

    public AccountIsEmptyException(String message, Throwable cause, int code) {
        super(message, cause, 416);
    }

    public AccountIsEmptyException(Throwable cause) {
        super(cause, 416);
    }
}
