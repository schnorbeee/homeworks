package com.norbertschmelhaus.tests;

import com.norbertschmelhaus.database.MobileInventory;
import com.norbertschmelhaus.dto.MobileType;
import com.norbertschmelhaus.enums.Coin;
import com.norbertschmelhaus.enums.Color;
import com.norbertschmelhaus.enums.ManufacturerEnum;
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
        Assert.assertEquals(0.0, inventory.getMobileQuantityByID(newMobileType.getId()), 0.0);
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
        Assert.assertEquals(0.0, inventory.getMobileQuantityByID(mobType.getId()), 0.0);
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
        Assert.assertEquals(16.0, inventory.getMobileQuantityByID(mobType.getId()), 0.0);
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
        Assert.assertEquals(6.0, inventory.getMobileQuantityByID(mobType.getId()), 0.0);
    }
    
}
