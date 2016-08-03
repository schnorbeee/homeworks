package com.norbertschmelhaus.eehomework2.beans;

import com.norbertschmelhaus.eehomework2.singletons.MobileInventory;
import com.norbertschmelhaus.eehomework2.singletons.UserDB;
import com.norbertschmelhaus.eehomework2.dto.MobileType;
import com.norbertschmelhaus.eehomework2.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.norbertschmelhaus.eehomework2.cart.CartService;
import com.norbertschmelhaus.eehomework2.enums.Coin;
import com.norbertschmelhaus.eehomework2.enums.Color;
import com.norbertschmelhaus.eehomework2.enums.ManufacturerEnum;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author norbeee
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    private static MobileInventory inventory;
    
    private static UserDB userDB;
    
    private static CartService cartService;
    
    private Main() {
        //Simple constuctor for main
    }

    public static void main(String[] args) throws IOException {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        
        inventory = container.instance().select(MobileInventory.class).get();
        userDB = container.instance().select(UserDB.class).get();
        cartService = container.instance().select(CartService.class).get();
        
        UserDTO[] users = serializeUsersFromJson();
        regUsers(users);
        MobileType[] mobiles = serializeMobileTypesFromJson();
        regMobiles(mobiles);

        cartTransactions();

        weld.shutdown();
    }

    private static void cartTransactions() {
        MobileType mobile1 = new MobileType(ManufacturerEnum.APPLE, "Iphone 5s", 100000, Coin.HUF, Color.BLACK);
        MobileType mobile2 = new MobileType(ManufacturerEnum.SAMSUNG, "Galaxy s4", 80000, Coin.HUF, Color.BLACK);

        mobile1 = inventory.addNewMobileType(mobile1);
        mobile2 = inventory.addNewMobileType(mobile2);

        inventory.returnMobile(mobile2, 33);
        inventory.returnMobile(mobile1, 50);
        inventory.reserveMobile(mobile2, 2);

        cartService.addMobile(inventory, mobile1.getId(), 5);
        cartService.addMobile(inventory, mobile2.getId(), 11);
        cartService.checkout();
    }

    private static void regUsers(UserDTO[] users) {
        Map<String, UserDTO> usersDB = new HashMap<>();
        for (UserDTO udto : users) {
            userDB.registrate(udto);
            usersDB.put(udto.getUserName(), udto);
        }
        writeOutUsers(usersDB);
    }

    private static void writeOutUsers(Map<String, UserDTO> users) {
        for (Map.Entry<String, UserDTO> user : users.entrySet()) {
            LOGGER.log(Level.INFO, "{0} : {1} : {2}", new Object[]{user.getKey(), userDB.getUser(user.getKey()).getEmail(), userDB.getUser(user.getKey()).getPassword()});
        }
    }

    private static void regMobiles(MobileType[] mobiles) {
        Map<String, Map<MobileType, Integer>> mobiless = new HashMap<>();
        for (MobileType mobile : mobiles) {
            inventory.addNewMobileType(mobile);
            Map<MobileType, Integer> mobileMap = inventory.getMobileWithQuantity(mobile.getId());
            mobiless.put(mobile.getId(), mobileMap);
        }
        writeOutMobiles(mobiless);
    }

    private static void writeOutMobiles(Map<String, Map<MobileType, Integer>> mobiles) {
        for (Map.Entry<String, Map<MobileType, Integer>> mobile : mobiles.entrySet()) {
            Map<MobileType, Integer> mobileMap = inventory.getMobileWithQuantity(mobile.getKey());
            MobileType currentMobile = mobileMap.entrySet().iterator().next().getKey();
            int current = mobileMap.entrySet().iterator().next().getValue();
            LOGGER.log(Level.INFO, "{0} : {1} : {2} : {3}", new Object[]{mobile.getKey(), currentMobile.getPrice(), currentMobile.getManufacturer(), current});
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
