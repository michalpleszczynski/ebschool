package com.ebschool.model;

import com.ebschool.utils.Identifiable;

import javax.persistence.*;
import java.util.Set;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 7:23 PM
 */
@Entity
@Table(name = "class_info")
public class ClassInfo implements Identifiable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "where_")
    private String where;

    @Column(name = "when_")
    private long when;

    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "classes")
    private Set<Student> students;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "classes")
    private Set<Teacher> teachers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "classInfo")
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

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

}
