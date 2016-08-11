package com.norbertschmelhaus.school.main;

import com.norbertschmelhaus.school.entitys.Boss;
import com.norbertschmelhaus.school.entitys.School;
import com.norbertschmelhaus.school.entitys.Student;
import com.norbertschmelhaus.school.entitys.Teacher;
import com.norbertschmelhaus.school.entitys.Worker;
import com.norbertschmelhaus.school.enums.Post;
import com.norbertschmelhaus.school.enums.Subject;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String DATABASE_NAME = "SchoolPU";
    
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(DATABASE_NAME);
    private static final EntityManager EM = EMF.createEntityManager();
    private static final EntityTransaction ET = EM.getTransaction();
    
    private static School school;
    private static Boss boss;
    private static Student student;
    private static Student student1;
    private static Student student2;
    private static Teacher teacher;
    private static Teacher teacher1;
    private static Teacher teacher2;
    private static Worker worker;
    private static Worker worker1; 
    
    private Main() {
        //Default constructor
    }
    
    public static void preDeclaredValues() {
        school = new School("Iskola");
        boss = new Boss(school, 500000);
        student = new Student("3A", "BMX", school);
        student1 = new Student("2B", "football", school);
        student2 = new Student("3A", "basketball", school);
        teacher = new Teacher(Subject.MATEMATIC, 24, 120000, school);
        teacher1 = new Teacher(Subject.GYMNASTIC, 10, 79000, school);
        teacher2 = new Teacher(Subject.FIZIK, 20, 100000, school);
        worker = new Worker(Post.DOORMAN, 120, 80000, school);
        worker1 = new Worker(Post.CLEANER, 100, 70000, school);
        
        Map<Subject, Integer> graderAtStudent1 = new EnumMap(Subject.class);
        graderAtStudent1.put(Subject.FIZIK, 4);
        graderAtStudent1.put(Subject.MATEMATIC, 5);
        graderAtStudent1.put(Subject.GYMNASTIC, 5);
        graderAtStudent1.put(Subject.HISTORY, 3);
        student.setGrades(graderAtStudent1);
    }
    
    public static void loggingNammedQueries() {
        LOGGER.log(Level.INFO, "Student list who study in this school: {0}",
                EM.createNamedQuery("Get_Student_List", Student.class).getResultList());
        LOGGER.log(Level.INFO, "Teacher list who work in this school: {0}",
                EM.createNamedQuery("Get_Teacher_List", School.class).getResultList());
        LOGGER.log(Level.INFO, "Worker list who work in this school: {0}",
                EM.createNamedQuery("Get_Worker_List", School.class).getResultList());
        LOGGER.log(Level.INFO, "Student who group 3A: {0}",
                EM.createNamedQuery("Student_Who_Department_3A", Student.class).getResultList());
        LOGGER.log(Level.INFO, "Student who has football hobby: {0}",
                EM.createNamedQuery("Student_Who_Hobby_football", Student.class).getResultList());
        LOGGER.log(Level.INFO, "Teacher who teach matematic: {0}",
                EM.createNamedQuery("Teacher_Who_Teach_Matematic", Teacher.class).setParameter("mat", Subject.MATEMATIC).getResultList());
        LOGGER.log(Level.INFO, "Teacher who had over 80000 his peyment: {0}",
                EM.createNamedQuery("Teacher_Who_Had_Over_8000_Payment", Teacher.class).getResultList());
        LOGGER.log(Level.INFO, "Worker who firstname is Nagy: {0}",
                EM.createNamedQuery("Worker_Who_Name_Nagy", Worker.class).getResultList());
        LOGGER.log(Level.INFO, "Worker who post is CLEANER: {0}",
                EM.createNamedQuery("Worker_Who_Post_CLEANER", Worker.class).setParameter("cleaner", Post.CLEANER).getResultList());
        LOGGER.log(Level.INFO, "Student grades: {0}",
                EM.createNamedQuery("Get_Student_grades", Student.class).getResultList());
    }

    public static void main(String[] args) {
        ET.begin();
        
        preDeclaredValues();
        
        EM.persist(boss);
        EM.persist(student);
        EM.persist(student1);
        EM.persist(student2);
        EM.persist(teacher);
        EM.persist(teacher1);
        EM.persist(teacher2);
        EM.persist(worker);
        EM.persist(worker1);
        
        school.getStudents().add(student);
        school.getStudents().add(student1);
        school.getStudents().add(student2);
        school.getTeachers().add(teacher);
        school.getTeachers().add(teacher1);
        school.getTeachers().add(teacher2);
        school.getWorkers().add(worker);
        school.getWorkers().add(worker1);
        
        student.setSchool(school);
        EM.merge(student);
        student1.setSchool(school);
        EM.merge(student1);
        student2.setSchool(school);
        EM.merge(student2);
        teacher.setSchool(school);
        EM.merge(teacher);
        teacher1.setSchool(school);
        EM.merge(teacher1);
        teacher2.setSchool(school);
        EM.merge(teacher2);
        worker.setSchool(school);
        worker.setFirstName("Nagy");
        EM.merge(worker);
        worker1.setSchool(school);
        EM.merge(worker1);
        
        ET.commit();
        
        loggingNammedQueries();
        
        EM.close();
        EMF.close();
    }
}
