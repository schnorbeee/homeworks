package com.norbertschmelhaus.eehomework2.singletons;

import com.norbertschmelhaus.eehomework2.dto.MobileType;
import com.norbertschmelhaus.eehomework2.interceptor.BeanValidation;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author norbeee
 */
public class MobileInventory {

    private final Map<String, Map<MobileType, Integer>> mobiles = new HashMap<>();
   
    @BeanValidation
    public Map<MobileType, Integer> getMobileWithQuantity(String id) {
        return mobiles.get(id);
    }

    @BeanValidation
    public MobileType addNewMobileType(MobileType type) {
        type.setId(UUID.randomUUID().toString());
        Map<MobileType, Integer> mobileWithQuantity = new HashMap();
        mobileWithQuantity.put(type, 0);
        mobiles.put(type.getId(), mobileWithQuantity);
        return type;
    }

    @BeanValidation
    public boolean reserveMobile(MobileType type, int quantity) {
        if (mobiles.get(type.getId()) != null && mobiles.get(type.getId()).get(type) > quantity) {
            return mobiles.get(type.getId()).replace(type, mobiles.get(type.getId()).get(type), mobiles.get(type.getId()).get(type) - quantity);
        }
        return false;
    }

    @BeanValidation
    public boolean returnMobile(MobileType type, int quantity) {
        if (mobiles.get(type.getId()) != null) {
            return mobiles.get(type.getId()).replace(type, mobiles.get(type.getId()).get(type), mobiles.get(type.getId()).get(type) + quantity);
        }
        return false;
    }
    
}
