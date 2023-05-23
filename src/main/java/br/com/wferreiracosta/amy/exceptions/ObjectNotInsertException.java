package br.com.wferreiracosta.amy.exceptions;

public class ObjectNotInsertException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ObjectNotInsertException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ObjectNotInsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotInsertException(String message) {
        super(message);
    }

    public ObjectNotInsertException(Throwable cause) {
        super(cause);
    }

}
