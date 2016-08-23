package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        //Default contructor
    }

    public BadRequestException(String message) {
        super(message);
    }

}
