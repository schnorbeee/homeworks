package com.norbertschmelhaus.daos;

import com.norbertschmelhaus.entitys.Guest;
import com.norbertschmelhaus.entitys.Happypark;
import com.norbertschmelhaus.entitys.Machine;
import com.norbertschmelhaus.enums.GuestStatus;
import com.norbertschmelhaus.exceptions.BadRequestException;
import com.norbertschmelhaus.exceptions.MachineInUseException;
import com.norbertschmelhaus.exceptions.MachineNotExistInThisParkException;
import com.norbertschmelhaus.exceptions.ParkAreaToSmallToAddMachineException;
import com.norbertschmelhaus.exceptions.ParkHaventAnyMoneyBuyMachineException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class HappyparkDaoImpl extends AbstractDao<Happypark> {

    private static final String BADREQUESTMESSAGE = "Not exist this happypark.";

    public HappyparkDaoImpl() {
        super(Happypark.class);
    }

    public Happypark addNewMachineById(Long id, Machine machine) {
        Happypark park = read(id);
        int usedArea = 0;
        if (null != park) {
            for (Machine m : park.getMachines()) {
                usedArea += m.getArea();
            }
            usedArea += machine.getArea();
            if (usedArea > park.getTotalArea()) {
                throw new ParkAreaToSmallToAddMachineException("Happypark haven't any area to add this machine.");
            }
            if (park.getCapital() < machine.getPriceOfMachine()) {
                throw new ParkHaventAnyMoneyBuyMachineException("Park hevan't any money to buy this machine.");
            }
            park.setCapital(park.getCapital() - machine.getPriceOfMachine());
            machine.setCurrentPark(park);
            em.merge(machine);
            return update(park);
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Machine> getlistUsedMachines(Long id) {
        Happypark park = read(id);
        if (null != park) {
            return em.createNamedQuery("List_of_machines", Machine.class).setParameter("park_pk", id).getResultList();
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public Happypark removeUnusedMachine(Long id, Machine machine) {
        Happypark park = read(id);
        badRequestChecker(park, machine);
        for (Machine m1 : park.getMachines()) {
            if (machine.getId().equals(m1.getId()) && machine.getFreeSeats() == machine.getSeats()) {
                machine.setCurrentPark(null);
                em.merge(machine);
                park.getMachines().remove(machine);
                park.setCapital(park.getCapital() + machine.getPriceOfMachine());
                return update(park);
            }
            if (machine.getId().equals(m1.getId()) && machine.getFreeSeats() != machine.getSeats()) {
                throw new MachineInUseException("Machine isn't empty.");
            }
        }
        throw new MachineNotExistInThisParkException("Happypark haven't this machine.");
    }

    public List<Guest> listOfActiveGuest(Long id) {
        Happypark park = read(id);
        if (null != park) {
            List<Guest> actives = new ArrayList();
            List<Guest> all = em.createNamedQuery("List_of_guest_who_dont_use_machine", Guest.class).setParameter("park_pk", id).getResultList();
            for (Guest g : all) {
                if (g.getStatus().equals(GuestStatus.REST)) {
                    actives.add(g);
                }
            }
            return actives;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public boolean badRequestChecker(Happypark park, Machine machine) {
        if (null == park || null == machine) {
            throw new BadRequestException(BADREQUESTMESSAGE);
        }
        return true;
    }

}
