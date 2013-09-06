package com.ebschool.rest.core.model;

import com.ebschool.ejb.model.Student;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "student")
public class StudentElement extends UserElement {

    private LevelElement level;
    private DetailedInfoElement detailedInfo;
    private Set<Long> classIds;
    private Set<Long> gradeIds;

    public StudentElement() {
        super();
    }

    public StudentElement (Student student) {
        super(student);
        LevelElement levelElement = new LevelElement(student.getLevel());
        setLevel(levelElement);
    }

    public LevelElement getLevel() {
        return level;
    }

    public void setLevel(LevelElement level) {
        this.level = level;
    }

    public DetailedInfoElement getDetailedInfo() {
        return detailedInfo;
    }

    @XmlElement(name = "detailedInfo")
    public void setDetailedInfo(DetailedInfoElement detailedInfo) {
        this.detailedInfo = detailedInfo;
    }

    public Set<Long> getClassIds() {
        return classIds;
    }

    @XmlElementWrapper(name = "classes")
    @XmlElement(name = "class")
    public void setClassIds(Set<Long> classIds) {
        this.classIds = classIds;
    }

    public Set<Long> getGradeIds() {
        return gradeIds;
    }

    @XmlElementWrapper(name = "grades")
    @XmlElement(name = "grade")
    public void setGradeIds(Set<Long> gradeIds) {
        this.gradeIds = gradeIds;
    }

}
