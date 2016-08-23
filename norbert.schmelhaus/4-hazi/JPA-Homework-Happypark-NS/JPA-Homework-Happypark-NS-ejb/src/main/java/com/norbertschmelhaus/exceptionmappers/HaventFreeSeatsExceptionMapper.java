package com.norbertschmelhaus.exceptionmappers;

import com.norbertschmelhaus.exceptions.ErrorMessageClass;
import com.norbertschmelhaus.exceptions.HaventFreeSeatsOnThisMashineException;
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
public class HaventFreeSeatsExceptionMapper implements ExceptionMapper<HaventFreeSeatsOnThisMashineException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(HaventFreeSeatsOnThisMashineException exception) {
        logger.log(Level.WARNING, "Machine is full: " + exception.getMessage(), exception);
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(new ErrorMessageClass(exception.getMessage() + ":" + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
