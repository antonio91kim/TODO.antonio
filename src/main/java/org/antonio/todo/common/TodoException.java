package org.antonio.todo.common;

public class TodoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int errorCode;

    public TodoException() {
        super();
    }

    public TodoException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
