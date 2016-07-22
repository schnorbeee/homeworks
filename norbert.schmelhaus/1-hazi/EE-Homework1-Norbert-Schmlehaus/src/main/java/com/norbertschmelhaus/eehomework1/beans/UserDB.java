package com.norbertschmelhaus.eehomework1.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author norbeee
 */
public class UserDB {

    private final List<UserDTO> users = new ArrayList();
    
    public UserDB() {
        //Empty constructor
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public UserDTO registrate(UserDTO user) {
        user.setRegistrationDate(new Date());
        users.add(user);        
        return user;
    }

    public UserDTO getUser(String username) {
        UserDTO user = null;
        for (UserDTO u : users) {
            if (username.equals(u.getUserName())) {
                user = u;
            }
        }
        return user;
    }

    boolean authenticate(String username, String password) {
        boolean isAuthentication = false;
        for (UserDTO u : users) {
            if (username.equals(u.getUserName()) && password.equals(u.getPassword())) {
                isAuthentication = true;
                break;
            }
        }
        return isAuthentication;
    }
}
