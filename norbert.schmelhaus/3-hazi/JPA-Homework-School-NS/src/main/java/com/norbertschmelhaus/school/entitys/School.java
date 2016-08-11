package com.norbertschmelhaus.school.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
public class School implements Serializable {

    @Id @GeneratedValue
    private Long id;
    
    @Column(length = 25)
    private String name;
   
    @OneToMany(mappedBy = "school", cascade = CascadeType.PERSIST)
    private List<Student> students = new ArrayList();
    
    @OneToMany(mappedBy = "school", cascade = CascadeType.PERSIST)
    private List<Teacher> teachers = new ArrayList();
    
    @OneToMany(mappedBy = "school", cascade = CascadeType.PERSIST)
    private List<Worker>  workers = new ArrayList();

    public School() {
        //Default constuctor
    }

    public School(String name) {
        this.name = name;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

}
