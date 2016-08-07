package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class UserArentAnAdminException extends RuntimeException {
    
    public UserArentAnAdminException() {
        //Default constructor without message
    }
    
    public UserArentAnAdminException(String message) {
        super(message);
    }
    
    public UserArentAnAdminException(Throwable cause) {
        super(cause);
    }
    
    public UserArentAnAdminException(String message, Throwable cause) {
        super(message, cause);
    }

}
