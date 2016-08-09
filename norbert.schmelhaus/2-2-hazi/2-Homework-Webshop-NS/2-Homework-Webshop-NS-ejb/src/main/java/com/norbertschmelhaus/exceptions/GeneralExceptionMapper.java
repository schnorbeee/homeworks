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
public class GeneralExceptionMapper implements ExceptionMapper<ViolationBeanException> {

    @Inject @LoggerQualifier
    private Logger logger;
    
    @Override
    public Response toResponse(ViolationBeanException exception) {
        logger.log(Level.WARNING, "General Exception: Constraint of DTO isn't valid. " + exception.getMessage(), exception);
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new ErrorMessageClass(exception.getMessage() + " : " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
