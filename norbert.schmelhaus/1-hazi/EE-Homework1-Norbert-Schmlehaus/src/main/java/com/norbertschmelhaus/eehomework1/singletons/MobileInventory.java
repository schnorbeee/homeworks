package com.norbertschmelhaus.eehomework1.singletons;

import com.norbertschmelhaus.eehomework1.dto.MobileType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author norbeee
 */
public class MobileInventory {

    private static final MobileInventory INSTANCE = new MobileInventory();
    
    private final Map<String, Map<MobileType, Integer>> mobiles = new HashMap<>();
    
    private MobileInventory() {
        //Default Constructor
    }
   
    public static MobileInventory getInstance() {
        return INSTANCE;
    }
    
    public Map<MobileType, Integer> getMobileWithQuantity(String id) {
        return mobiles.get(id);
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
