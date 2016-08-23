package com.norbertschmelhaus.entitys;

import com.norbertschmelhaus.id.GuestBookId;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
@AssociationOverrides({
    @AssociationOverride(name = "park_fk",
            joinColumns = @JoinColumn(name = "park_id")),
    @AssociationOverride(name = "guest_fk",
            joinColumns = @JoinColumn(name = "guest_id"))
})
@NamedQuery(name = "Registration_of_guest_in_happypark",
        query = "SELECT g FROM GuestBook g WHERE g.pk.guestFK = :guest_pk AND g.pk.parkFK = :park_pk")
public class GuestBook implements Serializable {

    @EmbeddedId
    private GuestBookId pk;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date registarion;

    @Column(length = 100)
    private String description;

    public GuestBook() {
        //default contructor
    }

    public GuestBook(GuestBookId pk, Date registarion, String description) {
        this.pk = pk;
        this.registarion = registarion;
        this.description = description;
    }

    public GuestBookId getPk() {
        return pk;
    }

    public void setPk(GuestBookId pk) {
        this.pk = pk;
    }

    public Happypark getParkFK() {
        return getPk().getParkFK();
    }

    public void setPark(Happypark park) {
        getPk().setParkFK(park);
    }

    public Guest getGuestFK() {
        return getPk().getGuestFK();
    }

    public void setGuest(Guest guest) {
        getPk().setGuestFK(guest);
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.pk);
        hash = 97 * hash + Objects.hashCode(this.registarion);
        hash = 97 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GuestBook other = (GuestBook) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.pk, other.pk)) {
            return false;
        }
        if (!Objects.equals(this.registarion, other.registarion)) {
            return false;
        }
        return true;
    }

}
