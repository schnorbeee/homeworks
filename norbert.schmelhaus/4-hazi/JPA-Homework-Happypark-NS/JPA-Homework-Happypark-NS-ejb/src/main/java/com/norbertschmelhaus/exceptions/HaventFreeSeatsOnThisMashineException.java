package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class HaventFreeSeatsOnThisMashineException extends RuntimeException {

    public HaventFreeSeatsOnThisMashineException() {
        //Default contructor
    }

    public HaventFreeSeatsOnThisMashineException(String message) {
        super(message);
    }

}
