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
public class UserArentLoggedInExceptionMapper implements ExceptionMapper<UserArentLoggedInException> {

    @Inject
    private Logger logger;
    
    @Override
    public Response toResponse(UserArentLoggedInException exception) {
        logger.log(Level.SEVERE, "This user aren't logged in.");
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorIdentity(exception.getMessage() + " : " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }
    
}
