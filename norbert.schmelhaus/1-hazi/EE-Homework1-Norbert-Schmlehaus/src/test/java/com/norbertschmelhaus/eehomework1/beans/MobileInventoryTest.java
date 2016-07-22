package com.norbertschmelhaus.eehomework1.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author norbeee
 */
public class MobileInventoryTest {

    private static final Logger LOGGER = Logger.getLogger(UserDBTest.class.getName());
    private MobileInventory instance;
    private final MobileType mobType = new MobileType(ManufacturerEnum.HTC, "type1", 1, Coin.JEN, Color.BLUE);

    @Before
    public void setUp() {
        instance = new MobileInventory();
        instance.getMobileTypes().put(mobType.getId(), 25);
    }

    /**
     * Test of addNewMobileType method, of class MobileInventory.
     */
    @Test
    public void testAddNewMobileType() {
        LOGGER.log(Level.INFO, "addNewMobileType");
        MobileType newMobileType = new MobileType(ManufacturerEnum.APPLE, "type2", 1, Coin.HUF, Color.WHITE);
        MobileType expResult = new MobileType(ManufacturerEnum.APPLE, "type2", 1, Coin.HUF, Color.WHITE);
        LOGGER.log(Level.INFO, expResult.getId());
        MobileType result = instance.addNewMobileType(newMobileType);
        Assert.assertEquals(expResult, result);
        Assert.assertEquals(0.0, instance.getMobileTypes().get(newMobileType.getId()), 0.0);
    }

    /**
     * Test of reserveMobile method, of class MobileInventory.
     */
    @Test
    public void testReserveMobile() {
        LOGGER.log(Level.INFO, "reserveMobile");
        int quantity = 4;
        boolean expResult = true;
        boolean result = instance.reserveMobile(mobType, quantity);
        Assert.assertEquals(expResult, result);
        Assert.assertEquals(21.0, (double) (instance.getMobileTypes().get(mobType.getId())), 0.0);
    }

    /**
     * Test of returnMobile method, of class MobileInventory.
     */
    @Test
    public void testReturnMobile() {
        LOGGER.log(Level.INFO, "returnMobile");
        int quantity = 6;
        boolean expResult = true;
        boolean result = instance.returnMobile(mobType, quantity);
        Assert.assertEquals(expResult, result);
        Assert.assertEquals(31.0, (double) (instance.getMobileTypes().get(mobType.getId())), 0.0);
    }

}
