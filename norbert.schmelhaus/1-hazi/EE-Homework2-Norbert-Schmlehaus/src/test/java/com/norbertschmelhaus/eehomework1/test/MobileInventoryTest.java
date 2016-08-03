package com.norbertschmelhaus.eehomework2.test;

import com.norbertschmelhaus.eehomework2.enums.ManufacturerEnum;
import com.norbertschmelhaus.eehomework2.enums.Color;
import com.norbertschmelhaus.eehomework2.enums.Coin;
import com.norbertschmelhaus.eehomework2.singletons.MobileInventory;
import com.norbertschmelhaus.eehomework2.dto.MobileType;
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

    private static final Logger LOGGER = Logger.getLogger(MobileInventoryTest.class.getName());
    private final MobileType mobType = new MobileType(ManufacturerEnum.HTC, "type1", 1, Coin.JEN, Color.BLUE);
    private MobileInventory inventory;
    
    @Before
    public void setUp() {
        inventory = new MobileInventory();
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
        MobileType result = inventory.addNewMobileType(newMobileType);
        Assert.assertEquals(expResult, result);
        Assert.assertEquals(0.0, inventory.getMobileWithQuantity(newMobileType.getId()).get(newMobileType), 0.0);
    }

    /**
     * Test of reserveMobile method, of class MobileInventory, with higher quantity.
     */
    @Test
    public void testReserveMobileWithHigherQuantity() {
        LOGGER.log(Level.INFO, "reserveMobile");
        int quantity = 4;
        inventory.addNewMobileType(mobType);
        boolean result = inventory.reserveMobile(mobType, quantity);
        Assert.assertFalse(result);
        Assert.assertEquals(0.0, inventory.getMobileWithQuantity(mobType.getId()).get(mobType), 0.0);
    }
    
    /**
     * Test of reserveMobile method, of class MobileInventory, with lower quantity.
     */
    @Test
    public void testReserveMobileWithLowerQuantity() {
        LOGGER.log(Level.INFO, "reserveMobile");
        int quantity = 4;
        inventory.addNewMobileType(mobType);
        inventory.returnMobile(mobType, 20);
        boolean result = inventory.reserveMobile(mobType, quantity);
        Assert.assertTrue(result);
        Assert.assertEquals(16.0, inventory.getMobileWithQuantity(mobType.getId()).get(mobType), 0.0);
    }

    /**
     * Test of returnMobile method, of class MobileInventory.
     */
    @Test
    public void testReturnMobile() {
        LOGGER.log(Level.INFO, "returnMobile");
        int quantity = 6;
        inventory.addNewMobileType(mobType);
        boolean result = inventory.returnMobile(mobType, quantity);
        Assert.assertTrue(result);
        Assert.assertEquals(6.0, inventory.getMobileWithQuantity(mobType.getId()).get(mobType), 0.0);
    }
    
}
