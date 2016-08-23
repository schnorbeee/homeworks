package com.norbertschmelhaus.resources;

import com.norbertschmelhaus.daos.AddressDaoImpl;
import com.norbertschmelhaus.dtos.AddressDTO;
import com.norbertschmelhaus.entitys.Address;
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
@Path("address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    @Inject
    private AddressDaoImpl addressDao;

    @POST
    @Path("add")
    public Response createNewAddress(Address address) {
        if (null != address) {
            addressDao.create(address);
            AddressDTO dto = new AddressDTO(address);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("search")
    public Response getAddressById(@QueryParam("address_pk") Long id) {
        Address address = addressDao.read(id);
        if (null != address) {
            AddressDTO dto = new AddressDTO(address);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Path("update")
    public Response updateAddressById(@QueryParam("address_pk") Long id, Address newAddress) {
        Address oldAddress = addressDao.read(id);
        if (null != oldAddress && null != newAddress) {
            oldAddress = newAddress;
            addressDao.update(oldAddress);
            AddressDTO dto = new AddressDTO(oldAddress);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Path("delete")
    public Response deleteAddressById(@QueryParam("address_pk") Long id) {
        Address deleteAddress = addressDao.read(id);
        if (null != deleteAddress) {
            addressDao.delete(deleteAddress);
            AddressDTO dto = new AddressDTO(deleteAddress);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
