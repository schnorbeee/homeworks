package com.norbertschmelhaus.school.entitys;

import com.norbertschmelhaus.school.enums.Subject;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Get_Student_List", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Get_Student_grades", query = "SELECT s.grades FROM Student s WHERE s.id = 3"),
    @NamedQuery(name = "Student_Who_Department_3A", query = "SELECT s FROM Student s WHERE s.department = '3A'"),
    @NamedQuery(name = "Student_Who_Hobby_football", query = "SELECT s FROM Student s WHERE s.hobby = 'football'")
})
public class Student extends People implements Serializable {
    
    @MapKeyEnumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "grades")
    @MapKeyColumn(name = "subject")
    @Column(name = "grade")
    private Map<Subject, Integer> grades = new EnumMap(Subject.class);
    
    
    @Id @GeneratedValue
    protected Long id;
    
    @Column(length = 3, nullable = false)
    private String department;
    
    private String hobby;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    public Student() {
        //Default constructor
    }

    public Student(String department, String hobby, School school) {
        this.department = department;
        this.hobby = hobby;
        this.school = school;
    }

    public Map<Subject, Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<Subject, Integer> grades) {
        this.grades = grades;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
    
}
