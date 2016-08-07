package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class UserArentLoggedInException extends RuntimeException {
    
    public UserArentLoggedInException() {
        //Default constructor without message
    }
    
    public UserArentLoggedInException(String message) {
        super(message);
    }
    
    public UserArentLoggedInException(Throwable cause) {
        super(cause);
    }
    
    public UserArentLoggedInException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
