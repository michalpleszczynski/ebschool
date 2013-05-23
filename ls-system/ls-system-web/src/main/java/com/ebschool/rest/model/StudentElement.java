package com.ebschool.rest.model;

import com.ebschool.model.Student;
import com.ebschool.rest.ResponseEntityBean;
import com.ebschool.service.StudentServiceLocal;

import javax.ejb.EJB;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "student")
public class StudentElement implements ResponseEntityBean<Student>{

    @EJB
    StudentServiceLocal studentService;

    private LevelElement level;
    private Set<GradeElement> grades;
    private Set<ClassInfoElement> classes;

    //TODO: find some more generic approach
    public static Set<StudentElement> buildSet(Set<Student> students){
        Set<StudentElement> returnSet = new HashSet<StudentElement>();
        for (Student student : students){
            StudentElement studentElement = new StudentElement();
            studentElement.init(student);
            returnSet.add(studentElement);
        }
        return returnSet;
    }

    @Override
    public void init(Student student) {
        student = studentService.getStudentById(student.getId());
        LevelElement levelElement = new LevelElement();
        levelElement.init(student.getLevel());
        setLevel(levelElement);
    }

    public LevelElement getLevel() {
        return level;
    }

    public void setLevel(LevelElement level) {
        this.level = level;
    }

    public Set<GradeElement> getGrades() {
        return grades;
    }

    public void setGrades(Set<GradeElement> grades) {
        this.grades = grades;
    }

    public Set<ClassInfoElement> getClasses() {
        return classes;
    }

    public void setClasses(Set<ClassInfoElement> classes) {
        this.classes = classes;
    }
}
