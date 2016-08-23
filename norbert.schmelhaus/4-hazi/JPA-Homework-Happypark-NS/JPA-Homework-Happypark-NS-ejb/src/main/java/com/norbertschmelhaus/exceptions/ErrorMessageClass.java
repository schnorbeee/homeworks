package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class ErrorMessageClass {

    private String message;

    public ErrorMessageClass() {
        //Default contructor
    }

    public ErrorMessageClass(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
