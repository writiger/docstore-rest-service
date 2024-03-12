package com.ds.common.exception;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-11 13:51
 */
public class RangeNotSatisfiable extends CommonException{
    public RangeNotSatisfiable(String message) {
        super(message, 416);
    }

    public RangeNotSatisfiable(String message, Throwable cause, int code) {
        super(message, cause, 416);
    }

    public RangeNotSatisfiable(Throwable cause) {
        super(cause, 416);
    }
}
