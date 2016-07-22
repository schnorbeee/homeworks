package com.norbertschmelhaus.eehomework1.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
        UserDB userDB = new UserDB();
        MobileInventory inventory = new MobileInventory();
        List<UserDTO> users;
        Map<String, Integer> mobilesType;
        Map<String, MobileType> mobiles;

        users = serializeUserDBFromJson();
        for (UserDTO udto : users) {
            userDB.getUsers().add(udto);
        }
        for (UserDTO udto2 : userDB.getUsers()) {
            LOGGER.log(Level.INFO, "{0} , {1} , {2} , {3}", new Object[]{udto2.getUserName(), udto2.getEmail(), udto2.getPassword(), udto2.getRegistrationDate()});
        }

        mobilesType = serializeMobileTypeFromJsonWithQuantity();
        mobiles = serializeMobileTypeFromJsonWithTypes();
        for (Map.Entry<String, Integer> str1 : mobilesType.entrySet()) {
            String key = str1.getKey();
            int quantity = str1.getValue();
            inventory.getMobileTypes().put(key, quantity);
        }
        for (Map.Entry<String, MobileType> str2 : mobiles.entrySet()) {
            String key = str2.getKey();
            MobileType type = str2.getValue();
            inventory.getMobiles().put(key, type);
        }
        for (String str3 : inventory.getMobileTypes().keySet()) {
            LOGGER.log(Level.INFO, "{0} : {1} : {2} : {3}", new Object[]{str3, mobiles.get(str3).getManufacturer(), mobiles.get(str3).getType(), mobilesType.get(str3)});
        }
    }

    private static List<UserDTO> serializeUserDBFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserDB users = mapper.readValue(Main.class.getClassLoader().getResource("jsons/UserDB-json.json"), UserDB.class);
        return users.getUsers();
    }

    private static Map<String, Integer> serializeMobileTypeFromJsonWithQuantity() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MobileInventory mobiles = mapper.readValue(Main.class.getClassLoader().getResource("jsons/MobileInventoryWithQuantity.json"), MobileInventory.class);
        return mobiles.getMobileTypes();
    }

    private static Map<String, MobileType> serializeMobileTypeFromJsonWithTypes() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MobileInventory mobiles = mapper.readValue(Main.class.getClassLoader().getResource("jsons/MobileInventoryWithType.json"), MobileInventory.class);
        return mobiles.getMobiles();
    }
}
