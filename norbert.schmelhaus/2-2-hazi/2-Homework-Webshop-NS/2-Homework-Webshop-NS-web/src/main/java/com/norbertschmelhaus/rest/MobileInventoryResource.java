package com.norbertschmelhaus.rest;

import com.norbertschmelhaus.database.MobileInventory;
import com.norbertschmelhaus.database.UserDB;
import com.norbertschmelhaus.dto.UserDTO;
import com.norbertschmelhaus.exceptions.UserIsntLoggedInException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@RequestScoped
@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MobileInventoryResource implements Serializable {
    
    @Inject
    private MobileInventory inventory;
    
    @Inject
    private UserDB userDB;
    
    @GET
    @Path("/get/{id}")
    public int getMobileQuantityByID(@PathParam("id") String uuid) {
        return inventory.getMobileQuantityByID(uuid);
    }
    
    @POST
    @Path("/reserve/{id}")
    public boolean reserveMobile(@Context HttpServletRequest request, @PathParam("id") String uuid, int quantity) {
        HttpSession session = request.getSession(true);
        if(isLogin(session.getAttribute("userDTO"))) {
            return inventory.reserveMobile(inventory.getMobileTypeByID(uuid), quantity);
        }
        throw new UserIsntLoggedInException("You isn't logged in.");
    }
    
    @POST
    @Path("/return/{id}")
    public boolean returnMobile(@Context HttpServletRequest request, @PathParam("id") String uuid, int quantity) {
        HttpSession session = request.getSession(true);
        if(isLogin(session.getAttribute("userDTO"))) {
            return inventory.returnMobile(inventory.getMobileTypeByID(uuid), quantity);
        }
        throw new UserIsntLoggedInException("You isn't logged in.");
    }
    
    public boolean isLogin(Object userObject) {
        if ((userObject != null) && (userObject instanceof UserDTO)) {
            UserDTO user = (UserDTO) userObject;
            if (userDB.authenticate(user.getUserName(), user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
