package com.norbertschmelhaus.preconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.norbertschmelhaus.database.MobileInventory;
import com.norbertschmelhaus.database.UserDB;
import com.norbertschmelhaus.dto.MobileType;
import com.norbertschmelhaus.dto.UserDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author norbeee
 */
@Singleton
@Startup
public class PreConfig {

    private static final Logger LOGGER = Logger.getLogger(PreConfig.class.getSimpleName());

    private MobileInventory inventory;
    
    private UserDB userDB;
    
    private final ObjectMapper mapper = new ObjectMapper();
   
    @PostConstruct
    public void start() {
        LOGGER.log(Level.INFO, "Init started!");
        try {
            UserDTO[] users = serializeUsersFromJson();
            regUsers(users);
            MobileType[] mobiles = serializeMobileTypesFromJson();
            regMobiles(mobiles);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Files not found! : {0}", ex);
        }
        LOGGER.log(Level.INFO, "Init done!");
    }

    private void regUsers(UserDTO[] users) {
        for (UserDTO udto : users) {
            userDB.registrate(udto);
        }
    }

    private void regMobiles(MobileType[] mobiles) {
        for (MobileType mobile : mobiles) {
            inventory.addNewMobileType(mobile);
        }
    }

    private UserDTO[] serializeUsersFromJson() throws IOException {
        return mapper.readValue(PreConfig.class.getClassLoader().getResource("scripts/users.json"), UserDTO[].class);
    }

    private MobileType[] serializeMobileTypesFromJson() throws IOException {
        return mapper.readValue(PreConfig.class.getClassLoader().getResource("scripts/types.json"), MobileType[].class);
    }

    @Inject
    public void setMobileInventory(MobileInventory mobileInventory) {
        this.inventory = mobileInventory;
    }
 
    @Inject
    public void setUserDB(UserDB userDB) {
        this.userDB = userDB;
    }
}
