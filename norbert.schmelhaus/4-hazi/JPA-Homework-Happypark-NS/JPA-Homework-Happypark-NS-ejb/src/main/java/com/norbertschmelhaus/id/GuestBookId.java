package com.norbertschmelhaus.id;

import com.norbertschmelhaus.entitys.Guest;
import com.norbertschmelhaus.entitys.Happypark;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Embeddable
public class GuestBookId implements Serializable {

    @ManyToOne
    private Happypark parkFK;

    @ManyToOne
    private Guest guestFK;

    public Happypark getParkFK() {
        return parkFK;
    }

    public void setParkFK(Happypark parkFK) {
        this.parkFK = parkFK;
    }

    public Guest getGuestFK() {
        return guestFK;
    }

    public void setGuestFK(Guest guestFK) {
        this.guestFK = guestFK;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.parkFK);
        hash = 71 * hash + Objects.hashCode(this.guestFK);
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
        final GuestBookId other = (GuestBookId) obj;
        if (!Objects.equals(this.parkFK, other.parkFK)) {
            return false;
        }
        if (!Objects.equals(this.guestFK, other.guestFK)) {
            return false;
        }
        return true;
    }

}
