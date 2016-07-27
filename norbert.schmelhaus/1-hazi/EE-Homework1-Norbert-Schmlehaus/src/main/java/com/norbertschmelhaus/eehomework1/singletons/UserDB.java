package com.norbertschmelhaus.eehomework1.singletons;

import com.norbertschmelhaus.eehomework1.dto.UserDTO;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author norbeee
 */
public enum UserDB {
    
    INSTANCE;

    private final Map<String, UserDTO> users = new HashMap<>();
    
    public UserDTO registrate(UserDTO user) {
        user.setRegistrationDate(new Date());
        users.put(user.getUserName(), user);        
        return user;
    }

    public UserDTO getUser(String username) {
        return users.get(username);
    }

    public boolean authenticate(String username, String password) {
        return username.equals(users.get(username).getUserName()) && password.equals(users.get(username).getPassword());
    }
}
