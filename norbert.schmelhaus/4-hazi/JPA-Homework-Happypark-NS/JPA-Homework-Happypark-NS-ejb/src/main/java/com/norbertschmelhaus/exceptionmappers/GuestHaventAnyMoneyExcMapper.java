package com.norbertschmelhaus.exceptionmappers;

import com.norbertschmelhaus.exceptions.ErrorMessageClass;
import com.norbertschmelhaus.exceptions.GuestHaventAnyMoneyToRegistrateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Provider
public class GuestHaventAnyMoneyExcMapper implements ExceptionMapper<GuestHaventAnyMoneyToRegistrateException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(GuestHaventAnyMoneyToRegistrateException exception) {
        logger.log(Level.WARNING, "Guest have little money: " + exception.getMessage(), exception);
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(new ErrorMessageClass(exception.getMessage() + ":" + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
