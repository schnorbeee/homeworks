package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class GuestIsntActiveInThisParkException extends RuntimeException {

    public GuestIsntActiveInThisParkException() {
        //Default contructor
    }

    public GuestIsntActiveInThisParkException(String message) {
        super(message);
    }

}
