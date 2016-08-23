package com.norbertschmelhaus.entitys;

import com.norbertschmelhaus.enums.GuestStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "List_of_guest_who_use_one_machine",
            query = "SELECT g FROM Machine m INNER JOIN m.guests g WHERE m.id = :machine_pk"),
    @NamedQuery(name = "List_of_guest_who_dont_use_machine",
            query = "SELECT g FROM Happypark h INNER JOIN h.activeGuests g WHERE h.id = :park_pk")
})
public class Guest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id", unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GuestStatus status;

    @Column(nullable = false)
    private int money;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date registration;

    @Column(nullable = false, length = 3)
    private int age;

    private boolean active;

    @ManyToOne(targetEntity = Happypark.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "active_park")
    private Happypark currentPark;

    @ManyToOne(targetEntity = Machine.class)
    @JoinColumn(name = "active_machine")
    private Machine currentMachine;

    @Transient
    @OneToMany(cascade = CascadeType.ALL)
    private List<GuestBook> guestBookElement;

    public Guest() {
        //default contructor
    }

    public Guest(int money, Date registration, int age) {
        this.money = money;
        this.registration = registration;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuestStatus getStatus() {
        return status;
    }

    public void setStatus(GuestStatus status) {
        this.status = status;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<GuestBook> getGuestBookElement() {
        return guestBookElement;
    }

    public void setGuestBookElement(List<GuestBook> guestBookElement) {
        this.guestBookElement = guestBookElement;
    }

    public Happypark getCurrentPark() {
        return currentPark;
    }

    public void setCurrentPark(Happypark currentPark) {
        this.currentPark = currentPark;
    }

    public Machine getCurrentMachine() {
        return currentMachine;
    }

    public void setCurrentMachine(Machine currentMachine) {
        this.currentMachine = currentMachine;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + this.money;
        hash = 97 * hash + Objects.hashCode(this.registration);
        hash = 97 * hash + this.age;
        hash = 97 * hash + (this.active ? 1 : 0);
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
        final Guest other = (Guest) obj;
        if (this.money != other.money) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.registration, other.registration)) {
            return false;
        }
        return true;
    }

}
