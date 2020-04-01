package com.berezovska.petstore.model;

public class ApiResponse {
    private long code;
    private String type;
    private String message;


    public long getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public ApiResponse(long code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiResponse { code=" + getCode() + ", type=" + getType() + ", message=" + getMessage() + "}" ;
    }
}