package com.norbertschmelhaus.daos;

import com.norbertschmelhaus.entitys.Guest;
import com.norbertschmelhaus.entitys.Happypark;
import com.norbertschmelhaus.entitys.Machine;
import com.norbertschmelhaus.enums.GuestStatus;
import com.norbertschmelhaus.exceptions.BadRequestException;
import com.norbertschmelhaus.exceptions.GuestAgeIsntEnoughTryThisMachineException;
import com.norbertschmelhaus.exceptions.GuestHaventAnyMoneyToRegistrateException;
import com.norbertschmelhaus.exceptions.GuestDontUseThisMachineException;
import com.norbertschmelhaus.exceptions.GuestIsntActiveInThisParkException;
import com.norbertschmelhaus.exceptions.HaventFreeSeatsOnThisMashineException;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class GuestDaoImpl extends AbstractDao<Guest> {

    private static final String BADREQUESTMESSAGE = "We haven't this guest in the database.";

    public GuestDaoImpl() {
        super(Guest.class);
    }

    public Guest buyTicketIntoParkById(Long guestPK, Happypark park) {
        Guest actual = read(guestPK);
        if (null != actual && null != park) {
            if (park.getTicketPrice() <= actual.getMoney()) {
                park.setCapital(park.getCapital() + park.getTicketPrice());
                actual.setCurrentPark(park);
                actual.setMoney(actual.getMoney() - park.getTicketPrice());
                actual.setActive(true);
                actual.setStatus(GuestStatus.REST);
                return update(actual);
            }
            throw new GuestHaventAnyMoneyToRegistrateException("You haven't any money to buy your ticket.");
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public Guest removeGuestFromPark(Long guestPK, Happypark park) {
        Guest actual = read(guestPK);
        if (null != actual && null != park) {
            if (actual.isActive() && null == actual.getCurrentMachine()) {
                actual.setStatus(null);
                actual.setActive(false);
                actual.setCurrentPark(null);
                return update(actual);
            }
            throw new GuestIsntActiveInThisParkException("This guest isn't in this park.");
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public Guest buyTicketToMachineById(Long guestPK, Long parkPK, Machine machine) {
        Guest actual = read(guestPK);
        Happypark park = em.find(Happypark.class, parkPK);
        badRequestChecker(actual, park);
        for (Machine m : park.getMachines()) {
            if (m.getId().equals(machine.getId()) && guestChecker(actual, m)) {
                park.setCapital(park.getCapital() + m.getTicketPrice());
                m.incrementFreeSeats();
                actual.setMoney(actual.getMoney() - m.getTicketPrice());
                actual.setCurrentMachine(machine);
                actual.setStatus(GuestStatus.ON_MACHINE);
                return update(actual);
            }
        }
        throw new BadRequestException("Park isn't use this machine.");
    }

    public Guest removeGuestFromMachine(Long guestPK, Long machinePK) {
        Guest actual = read(guestPK);
        Machine machine = em.find(Machine.class, machinePK);
        if (null != actual) {
            for (Guest g : machine.getGuests()) {
                if (g.getId().equals(guestPK)) {
                    machine.decrementFreeSeats();
                    actual.setCurrentMachine(null);
                    actual.setStatus(GuestStatus.REST);
                    return update(actual);
                }
            }
            throw new GuestDontUseThisMachineException("This guest don't quit this machine, because he didn't use it.");
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public boolean badRequestChecker(Guest actual, Happypark park) {
        if (null == actual || null == park) {
            throw new BadRequestException(BADREQUESTMESSAGE);
        }
        if (park.getMachines().isEmpty()) {
            throw new BadRequestException("Park haven't any machine yet.");
        }
        return true;
    }

    public boolean guestChecker(Guest actual, Machine m) {
        if (m.getTicketPrice() > actual.getMoney()) {
            throw new GuestHaventAnyMoneyToRegistrateException("You haven't any money to buy ticket on this machine.");
        }
        if (m.getAgeLimit() > actual.getAge()) {
            throw new GuestAgeIsntEnoughTryThisMachineException("You are to young to try this machine.");
        }
        if (m.getFreeSeats() == 0) {
            throw new HaventFreeSeatsOnThisMashineException("Haven't free seats in this round.");
        }
        return true;
    }

}
