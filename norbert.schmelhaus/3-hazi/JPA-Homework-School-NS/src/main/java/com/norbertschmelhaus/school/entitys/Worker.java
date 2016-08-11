package com.norbertschmelhaus.school.entitys;

import com.norbertschmelhaus.school.enums.Post;
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
    @NamedQuery(name = "Get_Worker_List", query = "SELECT w FROM Worker w"),
    @NamedQuery(name = "Worker_Who_Name_Nagy", query = "SELECT w FROM Worker w WHERE w.firstName = 'Nagy'"),
    @NamedQuery(name = "Worker_Who_Post_CLEANER", query = "SELECT w FROM Worker w WHERE w.post = :cleaner")
})
public class Worker extends People implements Serializable {
    @Id @GeneratedValue
    protected Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Post post;
    
    @Column(name = "hour_of_work", nullable = false, length = 3)
    private int hoursOfWork;
    
    @Column(nullable = false, length = 6)
    private int payment;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    public Worker() {
        //Default constructor
    }

    public Worker(Post post, int hoursOfWork, int payment, School school) {
        this.post = post;
        this.hoursOfWork = hoursOfWork;
        this.payment = payment;
        this.school = school;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getHoursOfWork() {
        return hoursOfWork;
    }

    public void setHoursOfWork(int hoursOfWork) {
        this.hoursOfWork = hoursOfWork;
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
