package com.norbertschmelhaus.eehomework2.cart;

import com.norbertschmelhaus.eehomework2.dto.MobileType;
import com.norbertschmelhaus.eehomework2.singletons.MobileInventory;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remove;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class CartService {

    @Inject
    private Logger logger;

    private final Map<MobileType, Integer> cart = new HashMap<>();

    public CartService() {
        //Default constructor
    }

    public Map<MobileType, Integer> addMobile(MobileInventory inventory, String mobileID, int quantity) {
        MobileType current = inventory.getMobileWithQuantity(mobileID).entrySet().iterator().next().getKey();
        if (inventory.reserveMobile(current, quantity)) {
            cart.put(current, quantity);
        }
        return cart;
    }

    public Map<MobileType, Integer> removeMobile(MobileInventory inventory, String mobileID, int quantity) {
        MobileType current = inventory.getMobileWithQuantity(mobileID).entrySet().iterator().next().getKey();
        int currentQuantity = inventory.getMobileWithQuantity(mobileID).entrySet().iterator().next().getValue();
        if (quantity <= currentQuantity) {
            cart.replace(current, currentQuantity, currentQuantity - quantity);
        }
        return cart;
    }

    public void clearCart(MobileInventory inventory) {
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
