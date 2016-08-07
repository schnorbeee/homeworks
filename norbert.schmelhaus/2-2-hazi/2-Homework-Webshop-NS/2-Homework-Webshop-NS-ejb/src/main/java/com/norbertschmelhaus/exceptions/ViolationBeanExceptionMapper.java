package com.norbertschmelhaus.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class ViolationBeanExceptionMapper implements ExceptionMapper<ViolationBeanException> {

    @Inject
    private Logger logger;
    
    @Override
    public Response toResponse(ViolationBeanException exception) {
        logger.log(Level.SEVERE, "My own Violation Bean Exception", exception);
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(exception.getMessage() + " : " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }
    

}
