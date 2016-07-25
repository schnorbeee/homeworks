package com.norbertschmelhaus.eehomework1.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
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
    private static final MobileInventory INVENTORY = new MobileInventory();
    private static final UserDB USER_DB = new UserDB();

    private Main() {
        //Simple constuktor for main
    }

    public static void main(String[] args) throws IOException {
        UserDTO[] users;
        MobileType[] mobiles;

        regUsersAndWriteOut(users = serializeUsersFromJson());
        regMobilesAndWriteOut(mobiles = serializeMobileTypesFromJson());
    }
    
    private static void regUsersAndWriteOut(UserDTO[] users) {
        List<String> usernames = new ArrayList();
        for (UserDTO udto : users) {
            USER_DB.registrate(udto);
            usernames.add(udto.getUserName());
        }
        for (String str : usernames) {
            LOGGER.log(Level.INFO, "{0} : {1} : {2}", new Object[]{str, USER_DB.getUser(str).getEmail(), USER_DB.getUser(str).getPassword()});
        }
    }
    
    private static void regMobilesAndWriteOut(MobileType[] mobiles) {
        for (MobileType mobile : mobiles) {
            INVENTORY.addNewMobileType(mobile);
        }
        for (Map.Entry<String, Map<MobileType, Integer>> e : INVENTORY.getMobiles().entrySet()) {
            MobileType currentMobile = e.getValue().keySet().iterator().next();
            int current = e.getValue().get(currentMobile);
            LOGGER.log(Level.INFO, "{0} : {1} : {2} : {3}", new Object[]{e.getKey(), currentMobile.getType(), currentMobile.getManufacturer(), current});
        }
    }

    private static UserDTO[] serializeUsersFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO[] users = mapper.readValue(Main.class.getClassLoader().getResource("jsons/users.json"), UserDTO[].class);
        return users;
    }

    private static MobileType[] serializeMobileTypesFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MobileType[] mobiles = mapper.readValue(Main.class.getClassLoader().getResource("jsons/types.json"), MobileType[].class);
        return mobiles;
    }

}
