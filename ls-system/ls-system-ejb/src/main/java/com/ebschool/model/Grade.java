package com.ebschool.model;

import com.ebschool.utils.Identifiable;

import javax.persistence.*;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 8:03 PM
 */
@Entity
@Table(name = "grade")
public class Grade  implements Identifiable {

    @Id
    @GeneratedValue
    private long id;

    private byte precentage;
    private byte weight;
    private String comment;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Test test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getPrecentage() {
        return precentage;
    }

    public void setPrecentage(byte precentage) {
        this.precentage = precentage;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public byte getWeight() {
        return weight;
    }

    public void setWeight(byte weight) {
        this.weight = weight;
    }
}
