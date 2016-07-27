package com.norbertschmelhaus.eehomework1.test;

import com.norbertschmelhaus.eehomework1.singletons.UserDB;
import com.norbertschmelhaus.eehomework1.dto.UserDTO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author norbeee
 */
public class UserDBTest {

    private static final Logger LOGGER = Logger.getLogger(UserDBTest.class.getName());
    private final Date now = new Date();

    /**
     * Test of registrate method, of class UserDB.
     */
    @Test
    public void testRegistrate() {
        LOGGER.log(Level.INFO, "registrate");
        UserDTO user = new UserDTO("myusername", "Password123", "email@email.hu", now);
        UserDTO expUser = new UserDTO("myusername", "Password123", "email@email.hu", now);
        user.setAddress("1851 fsdfasf");
        expUser.setAddress("1851 fsdfasf");
        UserDTO result = UserDB.getInstance().registrate(user);
        Assert.assertEquals((double)(expUser.getRegistrationDate().getTime()), (double)(result.getRegistrationDate().getTime()), 5.0);
        Assert.assertEquals(expUser.getAddress(), result.getAddress());
        Assert.assertEquals(expUser.getUserName(), result.getUserName());
        Assert.assertEquals(expUser.getPassword(), result.getPassword());
        Assert.assertEquals(expUser.getEmail(), result.getEmail());
    }

    /**
     * Test of getUser method, of class UserDB.
     */
    @Test
    public void testGetUser() {
        LOGGER.log(Level.INFO, "getUser");
        String username = "myusername";
        UserDTO user = new UserDTO(username, "Password123", "email@email.hu", now);
        UserDB.getInstance().registrate(user);
        UserDTO expUser = new UserDTO(username, "Password123", "email@email.hu", now);
        UserDTO result = UserDB.getInstance().getUser(username);
        Assert.assertEquals(expUser.getUserName(), result.getUserName());
        Assert.assertEquals(expUser, result);
    }

    /**
     * Test of authenticate method, of class UserDB. with expResult = true
     */
    @Test
    public void testAuthenticateTrue() {
        LOGGER.log(Level.INFO, "authenticate");
        String username = "myusername";
        String password = "Password123";
        UserDTO user = new UserDTO(username, "Password123", "email@email.hu", now);
        UserDB.getInstance().registrate(user);
        boolean result = UserDB.getInstance().authenticate(username, password);
        Assert.assertTrue(result);
    }

    /**
     * Test of authenticate method, of class UserDB. with expResult = false
     */
    @Test
    public void testAuthenticateFalse() {
        LOGGER.log(Level.INFO, "authenticate");
        String username = "myusername";
        String password = "Password1";
        UserDTO user = new UserDTO(username, "Password123", "email@email.hu", now);
        UserDB.getInstance().registrate(user);
        boolean result = UserDB.getInstance().authenticate(username, password);
        Assert.assertFalse(result);
    }
}
