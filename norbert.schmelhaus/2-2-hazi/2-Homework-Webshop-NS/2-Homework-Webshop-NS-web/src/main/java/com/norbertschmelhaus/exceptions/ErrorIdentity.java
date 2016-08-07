package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class ErrorIdentity {

    private String errorMessage;

    public ErrorIdentity() {
        //Default constuctor
    }

    public ErrorIdentity(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
