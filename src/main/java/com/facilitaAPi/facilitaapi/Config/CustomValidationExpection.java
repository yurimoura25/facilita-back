package com.facilitaAPi.facilitaapi.Config;

public class CustomValidationExpection extends RuntimeException{
    private static long serialVersionUID = 1L;

    private String entity;
    private String message;

    public CustomValidationExpection() {};

    public CustomValidationExpection(String entity, String message) {
        this.entity = entity;
        this.message = message;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
