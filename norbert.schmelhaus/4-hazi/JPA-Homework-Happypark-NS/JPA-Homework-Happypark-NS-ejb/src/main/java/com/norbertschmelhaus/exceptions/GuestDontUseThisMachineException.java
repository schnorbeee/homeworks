package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class GuestDontUseThisMachineException extends RuntimeException {

    public GuestDontUseThisMachineException() {
        //Default contructor
    }

    public GuestDontUseThisMachineException(String message) {
        super(message);
    }

}
