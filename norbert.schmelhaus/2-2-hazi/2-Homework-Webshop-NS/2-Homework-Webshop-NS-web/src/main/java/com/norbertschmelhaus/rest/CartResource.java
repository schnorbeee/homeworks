package com.norbertschmelhaus.rest;

import com.norbertschmelhaus.database.UserDB;
import com.norbertschmelhaus.dto.MobileType;
import com.norbertschmelhaus.dto.UserDTO;
import com.norbertschmelhaus.exceptions.UserIsntLoggedInException;
import com.norbertschmelhaus.services.CartService;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
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
@Path("/cart")
@SessionScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource implements Serializable {

    private static final String EXCEPTION_MESSAGE = "You isn't logged in.";
    
    private static final String UDTO = "userDTO";
    
    @Inject
    private CartService cartService;

    @Inject
    private UserDB userDB;

    public CartResource() {
        //Default constructor
    }

    @POST
    @Path("/{id}")
    public Map<MobileType, Integer> addMobileToCart(@Context HttpServletRequest request, @PathParam("id") String id, int quantity) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(600);
        if (isLogin(session.getAttribute(UDTO))) {
            return cartService.addMobile(id, quantity);
        }
        throw new UserIsntLoggedInException(EXCEPTION_MESSAGE);
    }

    @POST
    @Path("/remove/{id}")
    public Map<MobileType, Integer> removeMobileToCart(@Context HttpServletRequest request, @PathParam("id") String id, int quantity) {
        HttpSession session = request.getSession(true);
        if (isLogin(session.getAttribute(UDTO))) {
            return cartService.removeMobile(id, quantity);
        }
        throw new UserIsntLoggedInException(EXCEPTION_MESSAGE);
    }

    @GET
    @Path("/sum")
    public int getCartSummaryCost(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (isLogin(session.getAttribute(UDTO))) {
            return cartService.getCartSummaryCost();
        }
        throw new UserIsntLoggedInException(EXCEPTION_MESSAGE);
    }

    @POST
    @Path("/checkout")
    public Map<MobileType, Integer> checkout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (isLogin(session.getAttribute(UDTO))) {
            Map<MobileType, Integer> cart = cartService.checkout();
            session.invalidate();
            return cart;
        }
        throw new UserIsntLoggedInException(EXCEPTION_MESSAGE);
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
