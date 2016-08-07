package com.norbertschmelhaus.rest;

import com.norbertschmelhaus.database.MobileInventory;
import com.norbertschmelhaus.database.UserDB;
import com.norbertschmelhaus.dto.MobileType;
import com.norbertschmelhaus.dto.UserDTO;
import com.norbertschmelhaus.exceptions.UserArentAnAdminException;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@ApplicationScoped
@Path("/mobiletype")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MobileTypeResource implements Serializable {

    @Inject
    private MobileInventory inventory;
    
    @Inject
    private UserDB users;
    
    @POST
    @Path("/addMobile")
    public MobileType addNewMobileType(@Context HttpServletRequest request, MobileType mobile) {
        HttpSession session = request.getSession(true);
        if (isLoginAndIsAdmin(session.getAttribute("userDTO"))) {
            return inventory.addNewMobileType(mobile);
        }
        throw new UserArentAnAdminException("You haven't permission to add new mobile.");
    }
    
    @DELETE
    @Path("/{id}")
    public MobileType deleteMobileType(@Context HttpServletRequest request, @PathParam("id") String uuid) {
        HttpSession session = request.getSession(true);
        if (isLoginAndIsAdmin(session.getAttribute("userDTO"))) {
            return inventory.deleteMobileTypeByID(uuid);
        }
        throw  new UserArentAnAdminException("You haven't permission to delete mobile.");
    }
    
    @GET
    @Path("/{id}")
    public MobileType getMobileTypeByID(@PathParam("id") String uuid) {
        return inventory.getMobileTypeByID(uuid);
    }
    
    @GET
    public Map<String, Map<MobileType, Integer>> getAllMobileTypes() {
        return inventory.getMobiles();
    }
    
    public boolean isLoginAndIsAdmin(Object userObject) {
        if ((userObject != null) && (userObject instanceof UserDTO)) {
            UserDTO user = (UserDTO) userObject;
            if ((users.authenticate(user.getUserName(), user.getPassword())) && (user.isAdmin())) {
                return true;
            }
        }
        return false;
    }
}
