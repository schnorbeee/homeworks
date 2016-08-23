package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class MachineInUseException extends RuntimeException {

    public MachineInUseException() {
        //Default contructor
    }

    public MachineInUseException(String message) {
        super(message);
    }

}
