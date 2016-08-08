package com.norbertschmelhaus.database;

import com.norbertschmelhaus.dto.UserDTO;
import com.norbertschmelhaus.interceptors.BeanValidation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;

/**
 *
 * @author norbeee
 */
@Singleton
public class UserDB implements Serializable {
    
    private final Map<String, UserDTO> users = new HashMap<>();

    @BeanValidation
    public List<String> getUserNames() {
        List<String> userNames = new ArrayList();
        for(Map.Entry<String, UserDTO> id : users.entrySet()) {
            userNames.add(id.getKey());
        }
        return userNames;
    }
    
    @BeanValidation
    public UserDTO registrate(UserDTO user) {
        user.setRegistrationDate(new Date());
        users.put(user.getUserName(), user);        
        return user;
    }
    
    @BeanValidation
    public UserDTO deleteUser(String username) {
        return users.remove(username);
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
