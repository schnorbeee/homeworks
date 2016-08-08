package com.norbertschmelhaus.exceptions;

import com.norbertschmelhaus.qualifiers.LoggerQualifier;
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
public class UserIsntLoggedInExceptionMapper implements ExceptionMapper<UserIsntLoggedInException> {

    @Inject @LoggerQualifier
    private Logger logger;
    
    @Override
    public Response toResponse(UserIsntLoggedInException exception) {
        logger.log(Level.WARNING, "This user isn't logged in.");
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageClass(exception.getMessage() + " : " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }
    
}
