package com.ebschool.ejb.model;

import com.ebschool.ejb.utils.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 8:03 PM
 */
@Entity
@Table(name = "grade")
@NamedQueries({
        @NamedQuery(name = "findGradesByStudent", query = "SELECT g FROM Grade AS g WHERE g.student = :student"),
        @NamedQuery(name = "findGradesByStudentTask", query = "SELECT g FROM Grade AS g WHERE g.studentTask = :studentTask")
})
public class Grade  implements Identifiable, Serializable {

    public static final String GRADES_BY_STUDENT = "findGradesByStudent";
    public static final String GRADES_BY_STUDENT_TASK = "findGradesByStudentTask";

    public enum Related {
        STUDENT_TASK ("studentTask"),
        STUDENT ("student");

        private final String name;

        private Related(String name){
            this.name = name;
        }

        @Override
        public String toString(){
            return name;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte percentage;
    private byte weight;

    @Column(name = "comment_")
    private String comment;

    // grade does not have to be associated with a student task
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "student_task_id")
    private StudentTask studentTask;

    // it does have to be assigned to a student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Override
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

    public StudentTask getStudentTask() {
        return studentTask;
    }

    public void setStudentTask(StudentTask studentTask) {
        this.studentTask = studentTask;
    }

    public byte getWeight() {
        return weight;
    }

    public void setWeight(byte weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null ||
                !Grade.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final Grade grade = (Grade) object;
        if (!Objects.equals(getComment(), grade.getComment()))
            return false;
        if (!Objects.equals(getWeight(), grade.getWeight()))
            return false;
        if (!Objects.equals(getPercentage(), grade.getPercentage()))
            return false;
        return true;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + (getComment() != null ? getComment().hashCode() : 0);
        result = result*37 + getPercentage();
        result = result*37 + getWeight();
        return result;
    }

}
