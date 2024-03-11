package com.ds.common.exception;

import lombok.Data;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-11 14:46
 */
public class AccountAlreadyExist extends CommonException{
    public AccountAlreadyExist(String message) {
        super(message, 418);
    }

    public AccountAlreadyExist(String message, Throwable cause) {
        super(message, cause, 418);
    }

    public AccountAlreadyExist(Throwable cause) {
        super(cause, 418);
    }
}
