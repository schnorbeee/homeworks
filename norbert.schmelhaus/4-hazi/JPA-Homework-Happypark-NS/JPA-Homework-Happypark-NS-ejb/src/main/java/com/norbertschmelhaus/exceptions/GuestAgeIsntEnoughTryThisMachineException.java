package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class GuestAgeIsntEnoughTryThisMachineException extends RuntimeException {

    public GuestAgeIsntEnoughTryThisMachineException() {
        //Default contructor
    }

    public GuestAgeIsntEnoughTryThisMachineException(String message) {
        super(message);
    }

}
