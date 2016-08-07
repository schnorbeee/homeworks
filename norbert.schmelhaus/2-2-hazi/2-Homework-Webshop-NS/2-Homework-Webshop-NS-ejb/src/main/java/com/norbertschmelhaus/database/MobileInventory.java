package com.norbertschmelhaus.database;

import com.norbertschmelhaus.dto.MobileType;
import com.norbertschmelhaus.interceptors.BeanValidation;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.ejb.Singleton;

/**
 *
 * @author norbeee
 */
@Singleton
public class MobileInventory implements Serializable {

    private final transient Map<String, Map<MobileType, Integer>> mobiles = new HashMap<>();

    @BeanValidation
    public Map<String, Map<MobileType, Integer>> getMobiles() {
        return mobiles;
    }
   
    @BeanValidation
    public MobileType getMobileTypeByID(String id) {
        return mobiles.get(id).keySet().iterator().next();
    }
    
    @BeanValidation
    public int getMobileQuantityByID(String id) {
        return mobiles.get(id).entrySet().iterator().next().getValue();
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
    public MobileType deleteMobileTypeByID(String id) {
        return mobiles.remove(id).entrySet().iterator().next().getKey();
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
