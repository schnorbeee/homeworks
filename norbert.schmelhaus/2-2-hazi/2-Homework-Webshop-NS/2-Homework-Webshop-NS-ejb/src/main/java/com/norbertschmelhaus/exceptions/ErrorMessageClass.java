package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class ErrorMessageClass {

    private String errorMessage;

    public ErrorMessageClass() {
        //Default constuctor
    }

    public ErrorMessageClass(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
        
}
