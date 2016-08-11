package com.norbertschmelhaus.school.entitys;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
public class Boss extends People implements Serializable {

    @Id @GeneratedValue
    protected Long id;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private School school;
    
    private int payment;

    public Boss() {
        //Default constructor
    }

    public Boss(School school, int payment) {
        this.school = school;
        this.payment = payment;
    }
    
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
    
}
