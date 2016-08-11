package com.norbertschmelhaus.school.entitys;

import com.norbertschmelhaus.school.enums.Sex;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class People implements Serializable {

    @Column(name = "first_name", length = 30)
    protected String firstName;
    
    @Column(name = "last_name", length =  30)
    protected String lastName;
    
    @Column(length = 50)
    protected String email;
    
    @Enumerated(EnumType.STRING)
    protected Sex sex;
    
    @Transient
    protected int age;
    
    @Column(name = "birth_of_date")
    @Temporal(TemporalType.DATE)
    protected Date birthDay;

    public People() {
        //Default constructor
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    
    
    
}
