package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class MachineNotExistInThisParkException extends RuntimeException {

    public MachineNotExistInThisParkException() {
        //Default contructor
    }

    public MachineNotExistInThisParkException(String message) {
        super(message);
    }

}
