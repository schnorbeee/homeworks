package com.norbertschmelhaus.eehomework2.test;

import com.norbertschmelhaus.eehomework2.enums.ManufacturerEnum;
import com.norbertschmelhaus.eehomework2.enums.Color;
import com.norbertschmelhaus.eehomework2.enums.Coin;
import com.norbertschmelhaus.eehomework2.singletons.MobileInventory;
import com.norbertschmelhaus.eehomework2.dto.MobileType;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author norbeee
 */
public class MobileInventoryTest {

    private static final Logger LOGGER = Logger.getLogger(MobileInventoryTest.class.getName());
    private final MobileType mobType = new MobileType(ManufacturerEnum.HTC, "type1", 1, Coin.JEN, Color.BLUE);

    /**
     * Test of addNewMobileType method, of class MobileInventory.
     */
    @Test
    public void testAddNewMobileType() {
        LOGGER.log(Level.INFO, "addNewMobileType");
        MobileType newMobileType = new MobileType(ManufacturerEnum.APPLE, "type2", 1, Coin.HUF, Color.WHITE);
        MobileType expResult = new MobileType(ManufacturerEnum.APPLE, "type2", 1, Coin.HUF, Color.WHITE);
        LOGGER.log(Level.INFO, expResult.getId());
        MobileType result = MobileInventory.getInstance().addNewMobileType(newMobileType);
        Assert.assertEquals(expResult, result);
        Assert.assertEquals(0.0, MobileInventory.getInstance().getMobileWithQuantity(newMobileType.getId()).get(newMobileType), 0.0);
    }

    /**
     * Test of reserveMobile method, of class MobileInventory, with higher quantity.
     */
    @Test
    public void testReserveMobileWithHigherQuantity() {
        LOGGER.log(Level.INFO, "reserveMobile");
        int quantity = 4;
        MobileInventory.getInstance().addNewMobileType(mobType);
        boolean result = MobileInventory.getInstance().reserveMobile(mobType, quantity);
        Assert.assertFalse(result);
        Assert.assertEquals(0.0, MobileInventory.getInstance().getMobileWithQuantity(mobType.getId()).get(mobType), 0.0);
    }
    
    /**
     * Test of reserveMobile method, of class MobileInventory, with lower quantity.
     */
    @Test
    public void testReserveMobileWithLowerQuantity() {
        LOGGER.log(Level.INFO, "reserveMobile");
        int quantity = 4;
        MobileInventory.getInstance().addNewMobileType(mobType);
        MobileInventory.getInstance().returnMobile(mobType, 20);
        boolean result = MobileInventory.getInstance().reserveMobile(mobType, quantity);
        Assert.assertTrue(result);
        Assert.assertEquals(16.0, MobileInventory.getInstance().getMobileWithQuantity(mobType.getId()).get(mobType), 0.0);
    }

    /**
     * Test of returnMobile method, of class MobileInventory.
     */
    @Test
    public void testReturnMobile() {
        LOGGER.log(Level.INFO, "returnMobile");
        int quantity = 6;
        MobileInventory.getInstance().addNewMobileType(mobType);
        boolean result = MobileInventory.getInstance().returnMobile(mobType, quantity);
        Assert.assertTrue(result);
        Assert.assertEquals(6.0, MobileInventory.getInstance().getMobileWithQuantity(mobType.getId()).get(mobType), 0.0);
    }
    
}
