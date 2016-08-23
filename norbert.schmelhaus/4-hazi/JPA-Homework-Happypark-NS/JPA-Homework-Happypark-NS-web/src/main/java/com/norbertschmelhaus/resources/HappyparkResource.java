package com.norbertschmelhaus.resources;

import com.norbertschmelhaus.daos.HappyparkDaoImpl;
import com.norbertschmelhaus.daos.MachineDaoImpl;
import com.norbertschmelhaus.dtos.GuestDTO;
import com.norbertschmelhaus.dtos.HappyparkDTO;
import com.norbertschmelhaus.dtos.MachineDTO;
import com.norbertschmelhaus.entitys.Guest;
import com.norbertschmelhaus.entitys.Happypark;
import com.norbertschmelhaus.entitys.Machine;
import java.util.ArrayList;
import java.util.List;
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
@Path("parks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HappyparkResource {

    @Inject
    private HappyparkDaoImpl parkDao;

    @Inject
    private MachineDaoImpl machineDao;

    @POST
    @Path("add")
    public Response createNewPark(Happypark park) {
        if (null != park) {
            parkDao.create(park);
            HappyparkDTO dto = new HappyparkDTO(park);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("search")
    public Response getParkById(@QueryParam("park_pk") Long id) {
        Happypark park = parkDao.read(id);
        if (null != park) {
            HappyparkDTO dto = new HappyparkDTO(park);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Path("update")
    public Response updateHappyparkById(@QueryParam("park_pk") Long id, Happypark newPark) {
        Happypark oldPark = parkDao.read(id);
        if (null != oldPark && null != newPark) {
            oldPark = newPark;
            parkDao.update(oldPark);
            HappyparkDTO dto = new HappyparkDTO(oldPark);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Path("delete")
    public Response deleteHappyparkById(@QueryParam("park_pk") Long id) {
        Happypark deletePark = parkDao.read(id);
        if (null != deletePark) {
            parkDao.delete(deletePark);
            HappyparkDTO dto = new HappyparkDTO(deletePark);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @Path("add_machine")
    public Response addNewMachineInThePark(@QueryParam("park_pk") Long parkPK, @QueryParam("machine_pk") Long machinePK) {
        Happypark park = parkDao.read(parkPK);
        Machine machine = machineDao.read(machinePK);
        if (null != park && null != machine) {
            return Response.ok(new HappyparkDTO(parkDao.addNewMachineById(parkPK, machine))).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Path("remove_machine")
    public Response removeMachineInThePark(@QueryParam("park_pk") Long parkPK, @QueryParam("machine_pk") Long machinePK) {
        Happypark park = parkDao.read(parkPK);
        Machine machine = machineDao.read(machinePK);
        if (null != park && null != machine) {
            return Response.ok(new HappyparkDTO(parkDao.removeUnusedMachine(parkPK, machine))).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("machines")
    public Response getMachinesInThePark(@QueryParam("park_pk") Long id) {
        Happypark park = parkDao.read(id);
        if (null != park) {
            List<MachineDTO> machines = new ArrayList();
            for (Machine m : parkDao.getlistUsedMachines(id)) {
                MachineDTO machine = new MachineDTO(m);
                machines.add(machine);
            }
            return Response.ok(machines).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("unused_active_guests")
    public Response getGuestsWhoDontUseMachine(@QueryParam("park_pk") Long id) {
        Happypark park = parkDao.read(id);
        if (null != park) {
            List<GuestDTO> guests = new ArrayList();
            for (Guest g : parkDao.listOfActiveGuest(id)) {
                GuestDTO guest = new GuestDTO(g);
                guests.add(guest);
            }
            return Response.ok(guests).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("unused_active_guests_count")
    public Response getCountOfGuestWhoDontUseMachine(@QueryParam("park_pk") Long id) {
        Happypark park = parkDao.read(id);
        if (null != park) {
            return Response.ok(parkDao.listOfActiveGuest(id).size()).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
