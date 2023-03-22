package com.sojung.blog.response;

public class BaseResponse {
    private String timestamp;
    private String path;
    private Object body;
    private String message;
    private String error;
    private Integer status;

    public String getError() {
        return error;
    }

    public BaseResponse setError(String error) {
        this.error = error;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public BaseResponse setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    protected BaseResponse setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getPath() {
        return path;
    }

    protected BaseResponse setPath(String path) {
        this.path = path;
        return this;
    }

    public Object getBody() {
        return body;
    }

    public BaseResponse setBody(Object body) {
        this.body = body;
        return this;
    }

    public String getMessage() {
        return message;
    }

    protected BaseResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
