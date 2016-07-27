package com.norbertschmelhaus.eehomework1.beans;

import com.norbertschmelhaus.eehomework1.singletons.MobileInventory;
import com.norbertschmelhaus.eehomework1.singletons.UserDB;
import com.norbertschmelhaus.eehomework1.dto.MobileType;
import com.norbertschmelhaus.eehomework1.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author norbeee
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    private Main() {
        //Simple constuktor for main
    }

    public static void main(String[] args) throws IOException {
        UserDTO[] users = serializeUsersFromJson();
        regUsersAndWriteOut(users);
        MobileType[] mobiles = serializeMobileTypesFromJson();
        regMobilesAndWriteOut(mobiles);
    }
    
    private static void regUsersAndWriteOut(UserDTO[] users) {
        List<String> usernames = new ArrayList();
        for (UserDTO udto : users) {
            UserDB.getInstance().registrate(udto);
            usernames.add(udto.getUserName());
        }
        for (String str : usernames) {
            LOGGER.log(Level.INFO, "{0} : {1} : {2}", new Object[]{str, UserDB.getInstance().getUser(str).getEmail(), UserDB.getInstance().getUser(str).getPassword()});
        }
    }
    
    private static void regMobilesAndWriteOut(MobileType[] mobiles) {
        List<String> mobileUUIDs = new ArrayList();
        for (MobileType mobile : mobiles) {
            MobileInventory.getInstance().addNewMobileType(mobile);
            mobileUUIDs.add(mobile.getId());
        }
        for (String m : mobileUUIDs) {
            
            Map<MobileType, Integer> mobilesMap = MobileInventory.getInstance().getMobileWithQuantity(m);
            MobileType currentMobile = mobilesMap.entrySet().iterator().next().getKey();
            int current = mobilesMap.entrySet().iterator().next().getValue();
            LOGGER.log(Level.INFO, "{0} : {1} : {2} : {3}", new Object[]{m, currentMobile.getPrice(), currentMobile.getManufacturer(), current});
        }
    }

    private static UserDTO[] serializeUsersFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Main.class.getClassLoader().getResource("jsons/users.json"), UserDTO[].class);
    }

    private static MobileType[] serializeMobileTypesFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Main.class.getClassLoader().getResource("jsons/types.json"), MobileType[].class);
    }

}
