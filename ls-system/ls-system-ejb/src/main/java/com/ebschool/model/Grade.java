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
@NamedQueries({
        @NamedQuery(name = "findGradesByStudent", query = "SELECT g FROM Grade AS g WHERE g.student = :student")
})
public class Grade  implements Identifiable {

    public static final String GRADES_BY_STUDENT = "findGradesByStudent";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private byte percentage;
    private byte weight;

    @Column(name = "comment_")
    private String comment;

    // grade does not to be associated with a test
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private Test test;

    // it does have to be assigned to a student
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
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

    public byte getPercentage() {
        return percentage;
    }

    public void setPercentage(byte percentage) {
        this.percentage = percentage;
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
