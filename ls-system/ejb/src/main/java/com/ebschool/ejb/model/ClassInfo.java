package com.ebschool.ejb.model;

import com.ebschool.ejb.model.time.ClassTime;
import com.ebschool.ejb.model.time.Semester;
import com.ebschool.ejb.utils.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 7:23 PM
 */
@Entity
@Table(name = "class_info")
public class ClassInfo implements Identifiable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "where_")
    private String where;

    @Embedded
    private ClassTime when;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    private String description;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "class_student",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "teacher_class",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "classInfo")
    private Set<StudentTask> studentTasks;

    public ClassInfo(){
        students = new HashSet();
        teachers = new HashSet();
        studentTasks = new HashSet();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<StudentTask> getStudentTasks() {
        return studentTasks;
    }

    public void setStudentTasks(Set<StudentTask> studentTasks) {
        this.studentTasks = studentTasks;
    }

    public ClassTime getWhen() {
        return when;
    }

    public void setWhen(ClassTime when) {
        this.when = when;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null ||
                !ClassInfo.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final ClassInfo classInfo = (ClassInfo) object;
        if (!Objects.equals(getWhen(), classInfo.getWhen()))
            return false;
        if (!Objects.equals(getWhere(), classInfo.getWhere()))
            return false;
        if (!Objects.equals(getDescription(), classInfo.getDescription()))
            return false;
        if (!Objects.equals(getSemester(), classInfo.getSemester()))
            return false;
        if (!Objects.equals(getLevel(), classInfo.getLevel()))
            return false;
        return true;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + (getDescription() != null ? getDescription().hashCode() : 0);
        result = result*37 + (getWhen() != null ? getWhen().hashCode() : 0);
        result = result*37 + (getWhere() != null ? getWhere().hashCode() : 0);
        result = result*37 + (getSemester() != null ? getSemester().hashCode() : 0);
        result = result*37 + (getLevel() != null ? getLevel().hashCode(): 0);
        return result;
    }

}
