package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class ErrorDTO {

    private String errorMessage;

    public ErrorDTO() {
        //Default constuctor
    }

    public ErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
        
}
