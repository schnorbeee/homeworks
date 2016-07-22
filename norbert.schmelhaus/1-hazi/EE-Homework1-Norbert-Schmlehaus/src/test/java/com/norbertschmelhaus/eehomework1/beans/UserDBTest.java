package com.norbertschmelhaus.eehomework1.beans;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author norbeee
 */
public class UserDBTest {

    private static final Logger LOGGER = Logger.getLogger(UserDBTest.class.getName());
    private UserDB instance;
    private final Date now = new Date();

    @Before
    public void setUp() {
        instance = new UserDB();
        instance.getUsers().add(new UserDTO("myusername", "Password123", "email@email.hu", now));
    }

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
        UserDTO result = instance.registrate(user);
        Assert.assertEquals(expUser, result);
    }

    /**
     * Test of getUser method, of class UserDB.
     */
    @Test
    public void testGetUser() {
        LOGGER.log(Level.INFO, "getUser");
        String username = "myusername";
        UserDTO user = new UserDTO("myusername", "Password123", "email@email.hu", now);
        UserDTO result = instance.getUser(username);
        Assert.assertEquals(user.getUserName(), result.getUserName());
        Assert.assertEquals(user, result);
    }

    /**
     * Test of authenticate method, of class UserDB. with expResult = true
     */
    @Test
    public void testAuthenticateTrue() {
        LOGGER.log(Level.INFO, "authenticate");
        String username = "myusername";
        String password = "Password123";
        boolean expResult = true;
        boolean result = instance.authenticate(username, password);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of authenticate method, of class UserDB. with expResult = false
     */
    @Test
    public void testAuthenticateFalse() {
        LOGGER.log(Level.INFO, "authenticate");
        String username = "myusername";
        String password = "Password1";
        boolean expResult = false;
        boolean result = instance.authenticate(username, password);
        Assert.assertEquals(expResult, result);
    }
}
