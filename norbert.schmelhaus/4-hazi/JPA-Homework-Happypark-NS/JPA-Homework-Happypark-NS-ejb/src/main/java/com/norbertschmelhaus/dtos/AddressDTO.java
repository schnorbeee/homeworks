package com.norbertschmelhaus.dtos;

import com.norbertschmelhaus.entitys.Address;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class AddressDTO {

    private Long idDTO;
    private String zipCodeDTO;
    private String countryDTO;
    private String cityDTO;
    private String streetDTO;
    private String houseNumberDTO;

    public AddressDTO() {
        //default contructor
    }

    public AddressDTO(Address address) {
        this.idDTO = address.getId();
        this.zipCodeDTO = address.getZipCode();
        this.countryDTO = address.getCountry();
        this.cityDTO = address.getCity();
        this.streetDTO = address.getStreet();
        this.houseNumberDTO = address.getHouseNumber();
    }

    public Long getIdDTO() {
        return idDTO;
    }

    public void setIdDTO(Long idDTO) {
        this.idDTO = idDTO;
    }

    public String getZipCodeDTO() {
        return zipCodeDTO;
    }

    public void setZipCodeDTO(String zipCodeDTO) {
        this.zipCodeDTO = zipCodeDTO;
    }

    public String getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(String countryDTO) {
        this.countryDTO = countryDTO;
    }

    public String getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(String cityDTO) {
        this.cityDTO = cityDTO;
    }

    public String getStreetDTO() {
        return streetDTO;
    }

    public void setStreetDTO(String streetDTO) {
        this.streetDTO = streetDTO;
    }

    public String getHouseNumberDTO() {
        return houseNumberDTO;
    }

    public void setHouseNumberDTO(String houseNumberDTO) {
        this.houseNumberDTO = houseNumberDTO;
    }

}
