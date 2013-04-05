package com.ebschool.model;

import javax.persistence.*;
import java.util.Set;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 7:23 PM
 */
@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue
    private long id;

    private String when;
    private String where;
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "class_student",
        joinColumns = @JoinColumn(name = "class_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "classes")
    private Set<Teacher> teachers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "aClass")
    private Set<Test> tests;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
