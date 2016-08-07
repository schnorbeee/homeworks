package com.norbertschmelhaus.rest;

import com.norbertschmelhaus.database.UserDB;
import com.norbertschmelhaus.dto.UserDTO;
import com.norbertschmelhaus.exceptions.UserArentAnAdminException;
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
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserDTOResource {

    private static final String EXCEPTION_MESSAGE = "You haven't permission to delete user.";
    
    private static final String UDTO = "userDTO";
    
    @Inject
    private UserDB users;

    @POST
    @Path("/addUser")
    public UserDTO addUser(@Context HttpServletRequest request, UserDTO user) {
        HttpSession session = request.getSession(true);
        if (isLoginAndIsAdmin(session.getAttribute(UDTO))) {
            return users.registrate(user);
        }
        throw new UserArentAnAdminException(EXCEPTION_MESSAGE);
    }

    @DELETE
    @Path("/{username}")
    public UserDTO deleteUser(@Context HttpServletRequest request, @PathParam("username") String username) {
        HttpSession session = request.getSession(true);
        if (isLoginAndIsAdmin(session.getAttribute(UDTO))) {
            return users.deleteUser(username);
        }
        throw new UserArentAnAdminException(EXCEPTION_MESSAGE);
    }

    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam("username") String username) {
        return users.getUser(username);
    }

    @GET
    public Map<String, UserDTO> getUsers() {
        return users.getUsers();
    }
    
    @POST
    @Path("/login")
    public boolean login(@Context HttpServletRequest request, UserDTO user) {
        HttpSession session = request.getSession(true);
        if(users.authenticate(user.getUserName(), user.getPassword())) {
            session.setMaxInactiveInterval(600);
            session.setAttribute(UDTO, user);
            return true;
        }
        session.invalidate();
        return false;
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
