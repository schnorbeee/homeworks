package com.norbertschmelhaus.services;

import com.norbertschmelhaus.database.MobileInventory;
import com.norbertschmelhaus.dto.MobileType;
import com.norbertschmelhaus.qualifiers.LoggerQualifier;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateful
@StatefulTimeout(value = 2000, unit = TimeUnit.SECONDS)
public class CartService implements Serializable {

    @Inject @LoggerQualifier
    private transient Logger logger;

    private final Map<MobileType, Integer> cart = new HashMap<>();
    
    @Inject
    private MobileInventory inventory;

    public CartService() {
        //Default constructor
    }
    
    public void setInventory(MobileInventory inventory) {
        this.inventory = inventory;
    }

    public Map<MobileType, Integer> addMobile(String mobileID, int quantity) {
        MobileType current = inventory.getMobileTypeByID(mobileID);
        if (inventory.reserveMobile(current, quantity)) {
            cart.put(current, quantity);
        }
        return cart;
    }

    public Map<MobileType, Integer> removeMobile(String mobileID, int quantity) {
        MobileType current = inventory.getMobileTypeByID(mobileID);
        int currentQuantity = inventory.getMobileQuantityByID(mobileID);
        if (quantity <= currentQuantity && inventory.returnMobile(current, quantity)) {
            cart.replace(current, currentQuantity, currentQuantity - quantity);
            if(quantity == currentQuantity) {
                cart.remove(current);
            }
        }
        return cart;
    }

    public void clearCart() {
        for (Map.Entry<MobileType, Integer> m : cart.entrySet()) {
            inventory.returnMobile(m.getKey(), m.getValue());
        }
        cart.clear();
    }

    public int getCartSummaryCost() {
        int sum = 0;
        for (Map.Entry<MobileType, Integer> m : cart.entrySet()) {
            for (int i = 0; i < m.getValue(); i++) {
                sum += m.getKey().getPrice();
            }
        }
        return sum;
    }

    @Remove
    public Map<MobileType, Integer> checkout() {
        int sum = 0;
        for (Map.Entry<MobileType, Integer> mobile : cart.entrySet()) {
            logger.log(Level.INFO, "Mobile UUID: {0}, Quantity: {1}", new Object[]{mobile.getKey().getId(), mobile.getValue()});
            for (int i = 0; i < mobile.getValue(); i++) {
                sum += mobile.getKey().getPrice();
            }
        }
        logger.log(Level.INFO, "Sum price is: {0}", new Object[]{sum});
        return cart;
    }

}
