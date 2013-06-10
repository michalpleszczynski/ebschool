    package com.ebschool.rest.model;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.Level;
import com.ebschool.rest.utils.RestHelper;
import com.ebschool.service.ClassInfoServiceLocal;
import org.joda.time.DateTime;

import javax.ejb.EJB;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "class_info")
public class ClassInfoElement {

    private Long id;
    private String when;
    private String where;
    private String description;
    private LevelElement level;

    private Set<Long> students;
    private Set<Long> teachers;
    private Set<Long> tests;

    public ClassInfoElement() {}

    public ClassInfoElement (ClassInfo classInfo) {
        setId(classInfo.getId());
        setDescription(classInfo.getDescription());
        setWhen(RestHelper.convertLongDate(classInfo.getWhen()));
        setWhere(classInfo.getWhere());
        setLevel(new LevelElement(classInfo.getLevel()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getStudents() {
        return students;
    }

    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    public void setStudents(Set<Long> students) {
        this.students = students;
    }

    public Set<Long> getTeachers() {
        return teachers;
    }

    @XmlElementWrapper(name = "teachers")
    @XmlElement(name = "teacher")
    public void setTeachers(Set<Long> teachers) {
        this.teachers = teachers;
    }

    public Set<Long> getTests() {
        return tests;
    }

    @XmlElementWrapper(name = "tests")
    @XmlElement(name = "test")
    public void setTests(Set<Long> tests) {
        this.tests = tests;
    }

    public LevelElement getLevel() {
        return level;
    }

    public void setLevel(LevelElement level) {
        this.level = level;
    }
}
