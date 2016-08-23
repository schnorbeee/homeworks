package com.norbertschmelhaus.dtos;

import com.norbertschmelhaus.entitys.Guest;
import com.norbertschmelhaus.entitys.Happypark;
import com.norbertschmelhaus.enums.GuestStatus;
import java.util.Date;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class GuestDTO {

    private Long idDTO;
    private GuestStatus statusDTO;
    private int moneyDTO;
    private Date registrationDTO;
    private int ageDTO;
    private boolean activeDTO;
    private Happypark currentParkDTO;

    public GuestDTO() {
        //Default contructor
    }

    public GuestDTO(Guest guest) {
        this.idDTO = guest.getId();
        this.moneyDTO = guest.getMoney();
        this.registrationDTO = guest.getRegistration();
        this.ageDTO = guest.getAge();
    }

    public Long getIdDTO() {
        return idDTO;
    }

    public void setIdDTO(Long idDTO) {
        this.idDTO = idDTO;
    }

    public GuestStatus getStatusDTO() {
        return statusDTO;
    }

    public void setStatusDTO(GuestStatus statusDTO) {
        this.statusDTO = statusDTO;
    }

    public int getMoneyDTO() {
        return moneyDTO;
    }

    public void setMoneyDTO(int moneyDTO) {
        this.moneyDTO = moneyDTO;
    }

    public Date getRegistrationDTO() {
        return registrationDTO;
    }

    public void setRegistrationDTO(Date registrationDTO) {
        this.registrationDTO = registrationDTO;
    }

    public int getAgeDTO() {
        return ageDTO;
    }

    public void setAgeDTO(int ageDTO) {
        this.ageDTO = ageDTO;
    }

    public boolean isActiveDTO() {
        return activeDTO;
    }

    public void setActiveDTO(boolean activeDTO) {
        this.activeDTO = activeDTO;
    }

    public Happypark getCurrentParkDTO() {
        return currentParkDTO;
    }

    public void setCurrentParkDTO(Happypark currentParkDTO) {
        this.currentParkDTO = currentParkDTO;
    }

}
