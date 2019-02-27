package com.mraihaniqbal.bootcamp.springrms.pojo;

public class ResponseMap {

    private boolean success;
    private String message;
    private Long returnedId;

    public ResponseMap(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getReturnedId() {
        return returnedId;
    }

    public void setReturnedId(Long returnedId) {
        this.returnedId = returnedId;
    }
}
