package com.norbertschmelhaus.entitys;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
@NamedQuery(name = "List_of_machines",
        query = "SELECT m FROM Happypark h INNER JOIN h.machines m WHERE h.id = :park_pk")
public class Happypark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "park_id", unique = true)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @Column(nullable = false, length = 10)
    private int capital;

    @Column(name = "total_area", nullable = false, length = 10)
    private int totalArea;

    @Column(name = "ticket_price", nullable = false, length = 6)
    private int ticketPrice;

    @OneToMany(mappedBy = "currentPark", targetEntity = Machine.class)
    private List<Machine> machines;

    @OneToMany(mappedBy = "currentPark", targetEntity = Guest.class)
    private List<Guest> activeGuests;

    @Transient
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GuestBook> guestBookElements;

    public Happypark() {
        //Default contructor
    }

    public Happypark(String name, Address address, int capital, int area, int ticketPrice) {
        this.name = name;
        this.address = address;
        this.capital = capital;
        this.totalArea = area;
        this.ticketPrice = ticketPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public int getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(int totalArea) {
        this.totalArea = totalArea;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public List<Guest> getActiveGuests() {
        return activeGuests;
    }

    public void setActiveGuests(List<Guest> activeGuests) {
        this.activeGuests = activeGuests;
    }

    public List<GuestBook> getGuestBookElements() {
        return guestBookElements;
    }

    public void setGuestBookElements(List<GuestBook> guestBookElements) {
        this.guestBookElements = guestBookElements;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.address);
        hash = 71 * hash + this.capital;
        hash = 71 * hash + this.totalArea;
        hash = 71 * hash + this.ticketPrice;
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
        final Happypark other = (Happypark) obj;
        if (this.capital != other.capital) {
            return false;
        }
        if (this.totalArea != other.totalArea) {
            return false;
        }
        if (this.ticketPrice != other.ticketPrice) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }

}
