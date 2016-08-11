package com.norbertschmelhaus.school.entitys;

import com.norbertschmelhaus.school.enums.Subject;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Get_Teacher_List", query = "SELECT t FROM Teacher t"),
    @NamedQuery(name = "Teacher_Who_Teach_Matematic", query = "SELECT t FROM Teacher t WHERE t.teaching = :mat"),
    @NamedQuery(name = "Teacher_Who_Had_Over_8000_Payment", query = "SELECT t FROM Teacher t WHERE t.payment>80000")
})
public class Teacher extends People implements Serializable {

    @Id @GeneratedValue
    protected Long id;
    @Enumerated(EnumType.STRING)
    private Subject teaching;
    
    @Column(length = 3)
    private int hourQuantity;
    
    private int payment;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    public Teacher() {
        //Default constructor
    }

    public Teacher(Subject teaching, int hourQuantity, int payment, School school) {
        this.teaching = teaching;
        this.hourQuantity = hourQuantity;
        this.payment = payment;
        this.school = school;
    }

    public Subject getTeaching() {
        return teaching;
    }

    public void setTeaching(Subject teaching) {
        this.teaching = teaching;
    }

    public int getHourQuantity() {
        return hourQuantity;
    }

    public void setHourQuantity(int hourQuantity) {
        this.hourQuantity = hourQuantity;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
    
}
