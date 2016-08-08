package com.norbertschmelhaus.rest;

import com.norbertschmelhaus.database.UserDB;
import com.norbertschmelhaus.dto.UserDTO;
import com.norbertschmelhaus.exceptions.UserIsntAnAdminException;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
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
@RequestScoped
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserDTOResource {

    private static final String UDTO = "userDTO";
    
    @Inject
    private UserDB userDB;

    @POST
    @Path("/add")
    public UserDTO addUser(@Context HttpServletRequest request, UserDTO user) {
        HttpSession session = request.getSession(true);
        if (isLoginAndIsAdmin(session.getAttribute(UDTO))) {
            return userDB.registrate(user);
        }
        throw new UserIsntAnAdminException("You need admin privileges to add a user / Permission denied");
    }

    @DELETE
    @Path("/{username}")
    public UserDTO deleteUser(@Context HttpServletRequest request, @PathParam("username") String username) {
        HttpSession session = request.getSession(true);
        if (isLoginAndIsAdmin(session.getAttribute(UDTO))) {
            return userDB.deleteUser(username);
        }
        throw new UserIsntAnAdminException("You need admin privileges to delete a user / Permission denied");
    }

    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam("username") String username) {
        return userDB.getUser(username);
    }

    @GET
    public Map<String, String> getUsers() {
        Map<String, String> users = new HashMap();
        for(String userName : userDB.getUserNames()) {
            users.put(userName, userDB.getUser(userName).getEmail());
        }
        return users;
    }
    
    @POST
    @Path("/login")
    public boolean login(@Context HttpServletRequest request, UserDTO user) {
        HttpSession session = request.getSession(true);
        if(userDB.authenticate(user.getUserName(), user.getPassword())) {
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
            if ((userDB.authenticate(user.getUserName(), user.getPassword())) && (user.isAdmin())) {
                return true;
            }
        }
        return false;
    }

}
