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
        Assert.assertEquals(0.0, instance.getMobiles().get(newMobileType.getId()).get(newMobileType), 0.0);
    }

    /**
     * Test of reserveMobile method, of class MobileInventory, with higher quantity.
     */
    @Test
    public void testReserveMobileWithHigherQuantity() {
        LOGGER.log(Level.INFO, "reserveMobile");
        int quantity = 4;
        instance.addNewMobileType(mobType);
        boolean result = instance.reserveMobile(mobType, quantity);
        Assert.assertFalse(result);
        Assert.assertEquals(0.0, instance.getMobiles().get(mobType.getId()).get(mobType), 0.0);
    }
    
    /**
     * Test of reserveMobile method, of class MobileInventory, with lower quantity.
     */
    @Test
    public void testReserveMobileWithLowerQuantity() {
        LOGGER.log(Level.INFO, "reserveMobile");
        int quantity = 4;
        instance.addNewMobileType(mobType);
        instance.returnMobile(mobType, 20);
        boolean result = instance.reserveMobile(mobType, quantity);
        Assert.assertTrue(result);
        Assert.assertEquals(16.0, instance.getMobiles().get(mobType.getId()).get(mobType), 0.0);
    }

    /**
     * Test of returnMobile method, of class MobileInventory.
     */
    @Test
    public void testReturnMobile() {
        LOGGER.log(Level.INFO, "returnMobile");
        int quantity = 6;
        instance.addNewMobileType(mobType);
        boolean result = instance.returnMobile(mobType, quantity);
        Assert.assertTrue(result);
        Assert.assertEquals(6.0, instance.getMobiles().get(mobType.getId()).get(mobType), 0.0);
    }
    
}
