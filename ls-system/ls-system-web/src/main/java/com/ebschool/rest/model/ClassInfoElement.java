package com.ebschool.rest.model;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.Student;
import com.ebschool.rest.ResponseEntityBean;
import com.ebschool.service.ClassInfoServiceLocal;

import javax.ejb.EJB;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "class_info")
public class ClassInfoElement implements ResponseEntityBean<ClassInfo> {

    @EJB
    ClassInfoServiceLocal classInfoServiceLocal;

    private Long id;
    private long when;
    private String where;
    private String description;

    private Set<StudentElement> students;
    private Set<TeacherElement> teachers;
    private Set<TestElement> tests;

    public ClassInfoElement() {}

    //TODO: find some more generic approach
    public static Set<ClassInfoElement> buildSet(Set<ClassInfo> classInfos){
        Set<ClassInfoElement> returnSet = new HashSet<>();
        for (ClassInfo classInfo : classInfos){
            ClassInfoElement classInfoElement = new ClassInfoElement();
            classInfoElement.init(classInfo);
            returnSet.add(classInfoElement);
        }
        return returnSet;
    }

    @Override
    public void init(ClassInfo classInfo) {
        classInfo = classInfoServiceLocal.getById(classInfo.getId());
        setId(classInfo.getId());
        setDescription(classInfo.getDescription());
        setWhen(classInfo.getWhen());
        setWhere(classInfo.getWhere());
        StudentElement.buildSet(classInfo.getStudents());
        TeacherElement.buildSet(classInfo.getTeachers());
        TestElement.buildSet(classInfo.getTests());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StudentElement> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentElement> students) {
        this.students = students;
    }

    public Set<TeacherElement> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherElement> teachers) {
        this.teachers = teachers;
    }

    public Set<TestElement> getTests() {
        return tests;
    }

    public void setTests(Set<TestElement> tests) {
        this.tests = tests;
    }
}
