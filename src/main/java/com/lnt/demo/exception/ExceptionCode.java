package com.lnt.demo.exception;

public enum ExceptionCode {
    UNCATEGORIZED_EXCEPTION(500, "Uncategorized exception."),
    USER_EXISTED(401, "User existed."),
    USER_NOT_FOUND(402, "User not found."),
    USERNAME_REQUIRED(403, "Require username"),
    INVALID_PASSWORD(404, "Password must be at least 8 characters")
    ;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
