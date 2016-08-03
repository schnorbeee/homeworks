package com.norbertschmelhaus.eehomework2.singletons;

import com.norbertschmelhaus.eehomework2.dto.UserDTO;
import com.norbertschmelhaus.eehomework2.interceptor.BeanValidation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author norbeee
 */
public class UserDB {
    
    private final Map<String, UserDTO> users = new HashMap<>();
    
    @BeanValidation
    public UserDTO registrate(UserDTO user) {
        user.setRegistrationDate(new Date());
        users.put(user.getUserName(), user);        
        return user;
    }

    @BeanValidation
    public UserDTO getUser(String username) {
        return users.get(username);
    }

    @BeanValidation
    public boolean authenticate(String username, String password) {
        return username.equals(users.get(username).getUserName()) && password.equals(users.get(username).getPassword());
    }
}
