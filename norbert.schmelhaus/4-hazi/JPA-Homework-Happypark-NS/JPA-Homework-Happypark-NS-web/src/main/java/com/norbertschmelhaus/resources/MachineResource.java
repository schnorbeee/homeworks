package com.norbertschmelhaus.resources;

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
@Path("machines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MachineResource {

    @Inject
    private MachineDaoImpl machineDao;

    @POST
    @Path("add")
    public Response createNewMachine(Machine machine) {
        if (null != machine) {
            machineDao.create(machine);
            MachineDTO dto = new MachineDTO(machine);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("search")
    public Response getMachineById(@QueryParam("machine_pk") Long id) {
        Machine machine = machineDao.read(id);
        if (null != machine) {
            MachineDTO dto = new MachineDTO(machine);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Path("update")
    public Response updateMachineById(@QueryParam("machine_pk") Long id, Machine newMachine) {
        Machine oldMachine = machineDao.read(id);
        if (null != oldMachine && null != newMachine) {
            oldMachine = newMachine;
            machineDao.update(oldMachine);
            MachineDTO dto = new MachineDTO(oldMachine);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Path("delete")
    public Response deleteMachineById(@QueryParam("machine_pk") Long id) {
        Machine deleteMachine = machineDao.read(id);
        if (null != deleteMachine) {
            machineDao.delete(deleteMachine);
            MachineDTO dto = new MachineDTO(deleteMachine);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("park_use")
    public Response getListOfParksWhoUseThisMachine(@QueryParam("machine_pk") Long id) {
        Machine machine = machineDao.read(id);
        if (null != machine) {
            List<HappyparkDTO> parks = new ArrayList();
            for (Happypark h : machineDao.getlistOfparkWhoUseThisMachine(id)) {
                HappyparkDTO park = new HappyparkDTO(h);
                parks.add(park);
            }
            return Response.ok(parks).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("guest_use")
    public Response getListOfGuestWhoUseThisMachine(@QueryParam("machine_pk") Long id) {
        Machine machine = machineDao.read(id);
        if (null != machine) {
            List<GuestDTO> guests = new ArrayList();
            for (Guest g : machineDao.getlistGuestWhoUseMachine(id)) {
                GuestDTO guest = new GuestDTO(g);
                guests.add(guest);
            }
            return Response.ok(guests).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
