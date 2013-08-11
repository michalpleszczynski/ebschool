package com.ebschool.rest.model;

import com.ebschool.model.Grade;
import com.ebschool.model.Student;
import com.ebschool.service.GradeServiceLocal;

import javax.ejb.EJB;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "grade")
public class GradeElement {

    private Long id;
    private String comment;
    private StudentElement student;
    private byte weight;
    private byte percentage;

    public GradeElement() {}

    public GradeElement (Grade grade) {
        setId(grade.getId());
        setWeight(grade.getWeight());
        setComment(grade.getComment());
        setPercentage(grade.getPercentage());
        StudentElement studentElement = new StudentElement(grade.getStudent());
        setStudent(studentElement);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public StudentElement getStudent() {
        return student;
    }

    public void setStudent(StudentElement student) {
        this.student = student;
    }

    public byte getWeight() {
        return weight;
    }

    public void setWeight(byte weight) {
        this.weight = weight;
    }

    public byte getPercentage() {
        return percentage;
    }

    public void setPercentage(byte percentage) {
        this.percentage = percentage;
    }
}
