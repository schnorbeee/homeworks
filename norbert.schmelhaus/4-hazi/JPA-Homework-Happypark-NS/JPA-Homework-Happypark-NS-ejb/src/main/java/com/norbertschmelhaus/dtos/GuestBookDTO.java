package com.norbertschmelhaus.dtos;

import com.norbertschmelhaus.entitys.GuestBook;
import java.util.Date;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class GuestBookDTO {

    private Long guestPK;
    private Long parkPK;
    private Date registarion;
    private String description;

    public GuestBookDTO() {
        //Default constuctor
    }

    public GuestBookDTO(GuestBook book) {
        this.guestPK = book.getGuestFK().getId();
        this.parkPK = book.getParkFK().getId();
        this.registarion = book.getRegistarion();
        this.description = book.getDescription();
    }

    public Long getGuestPK() {
        return guestPK;
    }

    public void setGuestPK(Long guestPK) {
        this.guestPK = guestPK;
    }

    public Long getParkPK() {
        return parkPK;
    }

    public void setParkPK(Long parkPK) {
        this.parkPK = parkPK;
    }

    public Date getRegistarion() {
        return registarion;
    }

    public void setRegistarion(Date registarion) {
        this.registarion = registarion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
