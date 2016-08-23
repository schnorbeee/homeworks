package com.norbertschmelhaus.resources;

import com.norbertschmelhaus.daos.GuestDaoImpl;
import com.norbertschmelhaus.daos.HappyparkDaoImpl;
import com.norbertschmelhaus.daos.MachineDaoImpl;
import com.norbertschmelhaus.dtos.GuestDTO;
import com.norbertschmelhaus.entitys.Guest;
import com.norbertschmelhaus.entitys.Happypark;
import com.norbertschmelhaus.entitys.Machine;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
@Path("guests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GuestResource {

    @Inject
    private GuestDaoImpl guestDao;

    @Inject
    private HappyparkDaoImpl parkDao;

    @Inject
    private MachineDaoImpl machineDao;

    @POST
    @Path("add")
    public Response createNewGuest(Guest guest) {
        if (null != guest) {
            guestDao.create(guest);
            GuestDTO dto = new GuestDTO(guest);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("search")
    public Response getGuestById(@QueryParam("guest_pk") Long id) {
        Guest guest = guestDao.read(id);
        if (null != guest) {
            GuestDTO dto = new GuestDTO(guest);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Path("update")
    public Response updateGuestById(@QueryParam("guest_pk") Long id, Guest newGuest) {
        Guest oldGuest = guestDao.read(id);
        if (null != oldGuest && null != newGuest) {
            oldGuest = newGuest;
            guestDao.update(oldGuest);
            GuestDTO dto = new GuestDTO(oldGuest);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Path("delete")
    public Response deleteGuestById(@QueryParam("guest_pk") Long id) {
        Guest deleteGuest = guestDao.read(id);
        if (null != deleteGuest) {
            guestDao.delete(deleteGuest);
            GuestDTO dto = new GuestDTO(deleteGuest);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @Path("add_to_park")
    public Response addGuestToPark(@QueryParam("guest_pk") Long guestPK, @QueryParam("park_pk") Long parkPK) {
        Guest guest = guestDao.read(guestPK);
        Happypark park = parkDao.read(parkPK);
        if (null != guest && null != park) {
            return Response.ok(new GuestDTO(guestDao.buyTicketIntoParkById(guestPK, park))).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Path("remove_to_park")
    public Response removeGuestToPark(@QueryParam("guest_pk") Long guestPK, @QueryParam("park_pk") Long parkPK) {
        Guest guest = guestDao.read(guestPK);
        Happypark park = parkDao.read(parkPK);
        if (null != guest && null != park) {
            return Response.ok(new GuestDTO(guestDao.removeGuestFromPark(guestPK, park))).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @Path("add_to_machine")
    public Response addGuestToMachine(@QueryParam("guest_pk") Long guestPK, @QueryParam("park_pk") Long parkPK, @QueryParam("machine_pk") Long machinePK) {
        Guest guest = guestDao.read(guestPK);
        Machine machine = machineDao.read(machinePK);
        Happypark park = parkDao.read(parkPK);
        if (null != guest && null != machine && null != park) {
            return Response.ok(new GuestDTO(guestDao.buyTicketToMachineById(guestPK, parkPK, machine))).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Path("remove_to_machine")
    public Response removeGuestToMachine(@QueryParam("guest_pk") Long guestPK, @QueryParam("machine_pk") Long machinePK) {
        Guest guest = guestDao.read(guestPK);
        Machine machine = machineDao.read(machinePK);
        if (null != guest && null != machine) {
            return Response.ok(new GuestDTO(guestDao.removeGuestFromMachine(guestPK, machinePK))).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
