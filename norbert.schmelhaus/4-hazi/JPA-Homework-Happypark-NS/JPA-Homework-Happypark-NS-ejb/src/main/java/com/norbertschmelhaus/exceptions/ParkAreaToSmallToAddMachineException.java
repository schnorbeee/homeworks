package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class ParkAreaToSmallToAddMachineException extends RuntimeException {

    public ParkAreaToSmallToAddMachineException() {
        //Default contructor
    }

    public ParkAreaToSmallToAddMachineException(String message) {
        super(message);
    }

}
