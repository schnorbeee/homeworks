package com.norbertschmelhaus.dtos;

import com.norbertschmelhaus.entitys.Happypark;
import com.norbertschmelhaus.entitys.Machine;
import com.norbertschmelhaus.enums.TypeOfMachine;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class MachineDTO {

    private Long idDTO;
    private String nameDTO;
    private int areaDTO;
    private int ticketPriceDTO;
    private int seatsDTO;
    private int freeSeatsDTO;
    private TypeOfMachine typeDTO;
    private int ageLimitDTO;
    private int priceOfMachineDTO;
    private Happypark currentParkDTO;

    public MachineDTO() {
        //Default constructor
    }

    public MachineDTO(Machine machine) {
        this.idDTO = machine.getId();
        this.areaDTO = machine.getArea();
        this.ticketPriceDTO = machine.getTicketPrice();
        this.seatsDTO = machine.getSeats();
        this.typeDTO = machine.getType();
        this.priceOfMachineDTO = machine.getPriceOfMachine();
    }

    public Long getIdDTO() {
        return idDTO;
    }

    public void setIdDTO(Long idDTO) {
        this.idDTO = idDTO;
    }

    public String getNameDTO() {
        return nameDTO;
    }

    public void setNameDTO(String nameDTO) {
        this.nameDTO = nameDTO;
    }

    public int getAreaDTO() {
        return areaDTO;
    }

    public void setAreaDTO(int areaDTO) {
        this.areaDTO = areaDTO;
    }

    public int getTicketPriceDTO() {
        return ticketPriceDTO;
    }

    public void setTicketPriceDTO(int ticketPriceDTO) {
        this.ticketPriceDTO = ticketPriceDTO;
    }

    public int getSeatsDTO() {
        return seatsDTO;
    }

    public void setSeatsDTO(int seatsDTO) {
        this.seatsDTO = seatsDTO;
    }

    public int getFreeSeatsDTO() {
        return freeSeatsDTO;
    }

    public void setFreeSeatsDTO(int freeSeatsDTO) {
        this.freeSeatsDTO = freeSeatsDTO;
    }

    public TypeOfMachine getTypeDTO() {
        return typeDTO;
    }

    public void setTypeDTO(TypeOfMachine typeDTO) {
        this.typeDTO = typeDTO;
    }

    public int getAgeLimitDTO() {
        return ageLimitDTO;
    }

    public void setAgeLimitDTO(int ageLimitDTO) {
        this.ageLimitDTO = ageLimitDTO;
    }

    public int getPriceOfMachineDTO() {
        return priceOfMachineDTO;
    }

    public void setPriceOfMachineDTO(int priceOfMachineDTO) {
        this.priceOfMachineDTO = priceOfMachineDTO;
    }

    public Happypark getCurrentParkDTO() {
        return currentParkDTO;
    }

    public void setCurrentParkDTO(Happypark currentParkDTO) {
        this.currentParkDTO = currentParkDTO;
    }

}
