package com.norbertschmelhaus.eehomework2.singletons;

import com.norbertschmelhaus.eehomework2.dto.MobileType;
import com.norbertschmelhaus.eehomework2.interceptor.BeanValidation;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.inject.Singleton;

/**
 *
 * @author norbeee
 */
@Singleton
public class MobileInventory {

    private static final MobileInventory INSTANCE = new MobileInventory();
    
    private final Map<String, Map<MobileType, Integer>> mobiles = new HashMap<>();
   
    public static MobileInventory getInstance() {
        return INSTANCE;
    }
    
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
