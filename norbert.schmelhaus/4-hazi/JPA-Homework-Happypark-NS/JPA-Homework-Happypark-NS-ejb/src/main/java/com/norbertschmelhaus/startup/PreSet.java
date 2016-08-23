package com.norbertschmelhaus.startup;

import com.norbertschmelhaus.daos.AddressDaoImpl;
import com.norbertschmelhaus.daos.GuestDaoImpl;
import com.norbertschmelhaus.daos.HappyparkDaoImpl;
import com.norbertschmelhaus.daos.MachineDaoImpl;
import com.norbertschmelhaus.entitys.Address;
import com.norbertschmelhaus.entitys.Guest;
import com.norbertschmelhaus.entitys.Happypark;
import com.norbertschmelhaus.entitys.Machine;
import com.norbertschmelhaus.enums.TypeOfMachine;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Singleton
@Startup
public class PreSet {

    @Inject
    private Logger logger;

    @Inject
    private HappyparkDaoImpl parkDao;

    @Inject
    private MachineDaoImpl machineDao;

    @Inject
    private AddressDaoImpl addressDao;

    @Inject
    private GuestDaoImpl guestDao;

    private Happypark park1;
    private Happypark park2;
    private Machine machine1;
    private Machine machine2;
    private Machine machine3;
    private Guest guest1;
    private Guest guest2;
    private Guest guest3;
    private Address address1;
    private Address address2;
    private Address address3;

    @PostConstruct
    public void start() {
        logger.log(Level.INFO, "Init started!");
        initValues();
        persistComponents();
        logger.log(Level.INFO, "Init done!");
    }

    public void initValues() {
        address1 = new Address("8000", "HUN", "Komarom", "Bal utca", "15");
        address2 = new Address("8500", "HUN", "Papa", "Kis utca", "2");
        address3 = new Address("5100", "HUN", "Pecs", "Nagy utca", "1/11");
        park1 = new Happypark("Park1", address1, 1000000, 5900, 1000);
        park2 = new Happypark("Park2", address2, 800000, 2300, 800);
        guest1 = new Guest(10000, new Date(), 20);
        guest2 = new Guest(8000, new Date(), 16);
        guest3 = new Guest(2000, new Date(), 12);
        machine1 = new Machine(50, 400, 50, TypeOfMachine.CAROUSEL, 100000);
        machine2 = new Machine(100, 500, 25, TypeOfMachine.DODGEM, 300000);
        machine3 = new Machine(200, 1000, 100, TypeOfMachine.ROLLERCOASTER, 500000);
    }

    public void persistComponents() {

        addressDao.create(address1);
        addressDao.create(address2);
        addressDao.create(address3);
        parkDao.create(park1);
        parkDao.create(park2);
        machineDao.create(machine1);
        machineDao.create(machine2);
        machineDao.create(machine3);
        guestDao.create(guest1);
        guestDao.create(guest2);
        guestDao.create(guest3);

        machine2.setAgeLimit(12);
        machineDao.update(machine2);
        machine3.setAgeLimit(18);
        machineDao.update(machine3);
    }

}
