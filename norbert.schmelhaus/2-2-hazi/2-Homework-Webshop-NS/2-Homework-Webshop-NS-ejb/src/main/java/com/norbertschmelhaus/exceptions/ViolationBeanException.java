package com.norbertschmelhaus.exceptions;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class ViolationBeanException extends RuntimeException {
    
    public ViolationBeanException() {
        //Default constructor without message
    }
    
    public ViolationBeanException(String message) {
        super(message);
    }
    
    public ViolationBeanException(Throwable cause) {
        super(cause);
    }
    
    public ViolationBeanException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
