package com.norbertschmelhaus.entitys;

import com.norbertschmelhaus.enums.TypeOfMachine;
import java.io.Serializable;
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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
@NamedQuery(name = "List_of_happypark_who_use_this_machine",
        query = "SELECT h FROM Happypark h INNER JOIN h.machines m WHERE m.id = :machine_pk")
public class Machine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_id")
    private Long id;

    @Column(length = 30)
    private String name;

    @Column(nullable = false, length = 10)
    private int area;

    @Column(name = "ticket_price", nullable = false, length = 10)
    private int ticketPrice;

    @Column(nullable = false, length = 3)
    private int seats;

    @Column(nullable = false, length = 3)
    private int freeSeats;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOfMachine type;

    @Column(name = "age_limit", length = 3)
    private int ageLimit;

    @Column(name = "machine_price", nullable = false, length = 10)
    private int priceOfMachine;

    @ManyToOne(targetEntity = Happypark.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "park_id")
    private Happypark currentPark;

    @OneToMany(mappedBy = "currentMachine", targetEntity = Guest.class)
    private List<Guest> guests;

    public Machine() {
        //default contructor
    }

    public Machine(int area, int ticketPrice, int seats, TypeOfMachine type, int priceOfMachine) {
        this.area = area;
        this.ticketPrice = ticketPrice;
        this.seats = seats;
        this.freeSeats = seats;
        this.type = type;
        this.priceOfMachine = priceOfMachine;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public TypeOfMachine getType() {
        return type;
    }

    public void setType(TypeOfMachine type) {
        this.type = type;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getPriceOfMachine() {
        return priceOfMachine;
    }

    public void setPriceOfMachine(int priceOfMachine) {
        this.priceOfMachine = priceOfMachine;
    }

    public Happypark getCurrentPark() {
        return currentPark;
    }

    public void setCurrentPark(Happypark currentPark) {
        this.currentPark = currentPark;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void incrementFreeSeats() {
        this.freeSeats -= 1;
    }

    public void decrementFreeSeats() {
        this.freeSeats += 1;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + this.area;
        hash = 47 * hash + this.ticketPrice;
        hash = 47 * hash + this.seats;
        hash = 47 * hash + Objects.hashCode(this.type);
        hash = 47 * hash + this.ageLimit;
        hash = 47 * hash + this.priceOfMachine;
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
        final Machine other = (Machine) obj;
        if (this.area != other.area) {
            return false;
        }
        if (this.ticketPrice != other.ticketPrice) {
            return false;
        }
        if (this.seats != other.seats) {
            return false;
        }
        if (this.ageLimit != other.ageLimit) {
            return false;
        }
        if (this.priceOfMachine != other.priceOfMachine) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

}
