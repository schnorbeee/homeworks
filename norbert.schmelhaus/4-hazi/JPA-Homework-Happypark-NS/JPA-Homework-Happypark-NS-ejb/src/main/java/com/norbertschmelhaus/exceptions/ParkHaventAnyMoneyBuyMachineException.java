package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class ParkHaventAnyMoneyBuyMachineException extends RuntimeException {

    public ParkHaventAnyMoneyBuyMachineException() {
        //Default contructor
    }

    public ParkHaventAnyMoneyBuyMachineException(String message) {
        super(message);
    }

}
