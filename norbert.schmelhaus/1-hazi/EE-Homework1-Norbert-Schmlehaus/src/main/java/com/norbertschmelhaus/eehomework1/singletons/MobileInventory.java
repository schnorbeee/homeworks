package com.norbertschmelhaus.eehomework1.singletons;

import com.norbertschmelhaus.eehomework1.dto.MobileType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author norbeee
 */
public enum MobileInventory {

    INSTANCE;
    
    private final transient Map<String, Map<MobileType, Integer>> mobiles = new HashMap<>();    
    
    public Map<String, Map<MobileType, Integer>> getMobiles() {
        return mobiles;
    }

    public MobileType addNewMobileType(MobileType type) {
        type.setId(UUID.randomUUID().toString());
        Map<MobileType, Integer> mobileWithQuantity = new HashMap();
        mobileWithQuantity.put(type, 0);
        mobiles.put(type.getId(), mobileWithQuantity);
        return type;
    }

    public boolean reserveMobile(MobileType type, int quantity) {
        if (mobiles.get(type.getId()) != null && mobiles.get(type.getId()).get(type) > quantity) {
            return mobiles.get(type.getId()).replace(type, mobiles.get(type.getId()).get(type), mobiles.get(type.getId()).get(type) - quantity);
        }
        return false;
    }

    public boolean returnMobile(MobileType type, int quantity) {
        if (mobiles.get(type.getId()) != null) {
            return mobiles.get(type.getId()).replace(type, mobiles.get(type.getId()).get(type), mobiles.get(type.getId()).get(type) + quantity);
        }
        return false;
    }
}
