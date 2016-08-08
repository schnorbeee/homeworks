package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class UserIsntAnAdminException extends RuntimeException {
    
    public UserIsntAnAdminException() {
        //Default constructor without message
    }
    
    public UserIsntAnAdminException(String message) {
        super(message);
    }
    
    public UserIsntAnAdminException(Throwable cause) {
        super(cause);
    }
    
    public UserIsntAnAdminException(String message, Throwable cause) {
        super(message, cause);
    }

}
