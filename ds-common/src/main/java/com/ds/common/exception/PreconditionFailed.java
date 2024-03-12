package com.ds.common.exception;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-11 14:46
 */
public class PreconditionFailed extends CommonException{
    public PreconditionFailed(String message) {
        super(message, 412);
    }

    public PreconditionFailed(String message, Throwable cause) {
        super(message, cause, 412);
    }

    public PreconditionFailed(Throwable cause) {
        super(cause, 412);
    }
}
