package com.norbertschmelhaus.dtos;

import com.norbertschmelhaus.entitys.Address;
import com.norbertschmelhaus.entitys.Happypark;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class HappyparkDTO {

    private Long idDTO;
    private String nameDTO;
    private Address addressDTO;
    private int capitalDTO;
    private int totalAreaDTO;
    private int ticketPriceDTO;

    public HappyparkDTO() {
        //Default constructor
    }

    public HappyparkDTO(Happypark park) {
        this.nameDTO = park.getName();
        this.addressDTO = park.getAddress();
        this.capitalDTO = park.getCapital();
        this.totalAreaDTO = park.getTotalArea();
        this.ticketPriceDTO = park.getTicketPrice();
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

    public Address getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(Address addressDTO) {
        this.addressDTO = addressDTO;
    }

    public int getCapitalDTO() {
        return capitalDTO;
    }

    public void setCapitalDTO(int capitalDTO) {
        this.capitalDTO = capitalDTO;
    }

    public int getTotalAreaDTO() {
        return totalAreaDTO;
    }

    public void setTotalAreaDTO(int totalAreaDTO) {
        this.totalAreaDTO = totalAreaDTO;
    }

    public int getTicketPriceDTO() {
        return ticketPriceDTO;
    }

    public void setTicketPriceDTO(int ticketPriceDTO) {
        this.ticketPriceDTO = ticketPriceDTO;
    }

}
