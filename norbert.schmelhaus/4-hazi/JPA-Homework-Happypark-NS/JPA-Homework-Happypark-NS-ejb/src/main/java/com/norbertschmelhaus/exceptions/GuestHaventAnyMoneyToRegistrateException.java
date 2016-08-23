package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class GuestHaventAnyMoneyToRegistrateException extends RuntimeException {

    public GuestHaventAnyMoneyToRegistrateException() {
        //Default contructor
    }

    public GuestHaventAnyMoneyToRegistrateException(String message) {
        super(message);
    }

}
