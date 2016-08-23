package com.norbertschmelhaus.exceptionmappers;

import com.norbertschmelhaus.exceptions.ErrorMessageClass;
import com.norbertschmelhaus.exceptions.GuestAgeIsntEnoughTryThisMachineException;
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
public class GuestAgeIsntEnoughExcMapper implements ExceptionMapper<GuestAgeIsntEnoughTryThisMachineException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(GuestAgeIsntEnoughTryThisMachineException exception) {
        logger.log(Level.WARNING, "Guest is to young: " + exception.getMessage(), exception);
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(new ErrorMessageClass(exception.getMessage() + ":" + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
