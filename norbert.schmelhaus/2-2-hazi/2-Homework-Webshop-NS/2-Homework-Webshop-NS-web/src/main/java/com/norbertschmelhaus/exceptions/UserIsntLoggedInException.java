package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class UserIsntLoggedInException extends RuntimeException {
    
    public UserIsntLoggedInException() {
        //Default constructor without message
    }
    
    public UserIsntLoggedInException(String message) {
        super(message);
    }
    
    public UserIsntLoggedInException(Throwable cause) {
        super(cause);
    }
    
    public UserIsntLoggedInException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
