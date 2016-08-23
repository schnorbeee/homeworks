package com.norbertschmelhaus.resources;

import com.norbertschmelhaus.daos.GuestBookDaoImpl;
import com.norbertschmelhaus.daos.GuestDaoImpl;
import com.norbertschmelhaus.daos.HappyparkDaoImpl;
import com.norbertschmelhaus.dtos.GuestBookDTO;
import com.norbertschmelhaus.entitys.GuestBook;
import com.norbertschmelhaus.id.GuestBookId;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@RequestScoped
@Path("book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GuestBookRescource {

    @Inject
    private GuestBookDaoImpl bookDao;

    @Inject
    private HappyparkDaoImpl parkDao;

    @Inject
    private GuestDaoImpl guestDao;

    @GET
    @Path("get")
    public Response getDescriptionAbouthGuestInThisPark(@QueryParam("guest_pk") Long guestPK, @QueryParam("park_pk") Long parkPK) {
        if (null != guestPK && null != parkPK) {
            List<GuestBookDTO> desc = new ArrayList();
            for (GuestBook g : bookDao.getlistOfOneGuestOneParkGuestBookDesc(guestPK, parkPK)) {
                GuestBookDTO dto = new GuestBookDTO(g);
                desc.add(dto);
            }
            return Response.ok(desc).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @Path("add")
    public Response addNewBookDescription(@QueryParam("guest_pk") Long guestPK, @QueryParam("park_pk") Long parkPK, String desc) {
        GuestBookId pk = new GuestBookId();
        if (null != guestPK && null != parkPK) {
            pk.setGuestFK(guestDao.read(guestPK));
            pk.setParkFK(parkDao.read(parkPK));
            if (null != pk.getGuestFK() && null != pk.getParkFK()) {
                return Response.ok(new GuestBookDTO(bookDao.writeSomethingInGuestBook(pk, desc))).build();
            }
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
