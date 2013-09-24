package com.ebschool.ejb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 3/20/13
 * Time: 9:20 PM
 */
@Entity
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = "findStudentsByClass", query = "SELECT s FROM Student AS s WHERE :classInfo MEMBER OF s.classes"),
        @NamedQuery(name = "findStudentsByTeacher", query = "SELECT s FROM Student AS s INNER JOIN s.classes AS c WHERE :teacher MEMBER OF c.teachers"),
        @NamedQuery(name = "findStudentsByParent", query = "SELECT s FROM Student AS s, Parent AS p WHERE p = :parent AND s MEMBER OF p.childrenAccounts"),
        @NamedQuery(name = "findStudentsByLevel", query = "SELECT s FROM Student AS s WHERE s.level = :level")
})
public class Student extends User implements Serializable {

    public static final String STUDENTS_BY_CLASS = "findStudentsByClass";
    public static final String STUDENTS_BY_TEACHER = "findStudentsByTeacher";
    public static final String STUDENTS_BY_PARENT = "findStudentsByParent";
    public static final String STUDENTS_BY_LEVEL = "findStudentsByLevel";

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id", nullable = false, unique = true)
    private DetailedInfo detailedInfo;

    // student has a level assigned but can be assigned to classes with different levels
    // it's not a bug it's a feature :)
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "class_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    private Set<ClassInfo> classes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Grade> grades;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinTable(
            name = "parent_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Parent parent;

    public Student(){
        classes = new HashSet<ClassInfo>();
        grades = new HashSet<Grade>();
    }

    public DetailedInfo getDetailedInfo() {
        return detailedInfo;
    }

    public void setDetailedInfo(DetailedInfo detailedInfo) {
        this.detailedInfo = detailedInfo;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Set<ClassInfo> getClasses() {
        return classes;
    }

    public void setClasses(Set<ClassInfo> classes) {
        this.classes = classes;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null ||
                !Student.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final Student student = (Student) object;
        return getDetailedInfo() !=null ? getDetailedInfo().equals(student.getDetailedInfo()) : false;
    }

    @Override
    public int hashCode(){
        return getDetailedInfo() != null ? getDetailedInfo().hashCode() : 0;
    }
}
