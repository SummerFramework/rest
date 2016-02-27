package com.sf.rest.api.model.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by renan on 20/02/16.
 */
public class ErrorVO implements Serializable {

    @JsonProperty("status_code")
    private long statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("target")
    private Object target;

    @JsonProperty("errors")
    private List<ErrorMessageVO> errors;

    public ErrorVO(){

    }

    public ErrorVO(long statusCode, String message, Object target, List<ErrorMessageVO> errors) {
        this.statusCode = statusCode;
        this.message = message;
        this.target = target;
        this.errors = errors;
    }

    public ErrorVO (Exception e, long statusCode){
        this.message = e.getMessage();
        this.statusCode = statusCode;
    }

    public long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(long statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public List<ErrorMessageVO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessageVO> errors) {
        this.errors = errors;
    }
}