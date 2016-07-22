package com.norbertschmelhaus.eehomework1.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author norbeee
 */
public class MobileInventory {

    private final Map<String, MobileType> mobiles = new HashMap();
    private final Map<String, Integer> mobileTypes = new HashMap();
    
    public MobileInventory() {
        //Empty constructor
    }

    public Map<String, MobileType> getMobiles() {
        return mobiles;
    }

    public Map<String, Integer> getMobileTypes() {
        return mobileTypes;
    }

    public MobileType addNewMobileType(MobileType type) {
        type.setId(UUID.randomUUID().toString());
        mobileTypes.put(type.getId(), 0);
        mobiles.put(type.getId(), type);
        return type;
    }

    public boolean reserveMobile(MobileType type, int quantity) {
        if (mobileTypes.get(type.getId()) != null && mobileTypes.get(type.getId()) > quantity) {
            return mobileTypes.replace(type.getId(), mobileTypes.get(type.getId()), mobileTypes.get(type.getId()) - quantity);
        }
        return false;
    }

    public boolean returnMobile(MobileType type, int quantity) {
        if (mobileTypes.get(type.getId()) != null) {
            return mobileTypes.replace(type.getId(), mobileTypes.get(type.getId()), mobileTypes.get(type.getId()) + quantity);
        }
        return false;
    }
}
