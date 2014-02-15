package com.ebschool.web.beans.managers;

import com.ebschool.ejb.model.Grade;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.service.GradeService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * User: michau
 * Date: 2/13/14
 * Time: 9:07 PM
 */
@Named
@Stateless
public class GradeManager implements Serializable{

    @EJB
    GradeService gradeService;

    @Inject
    StudentManager studentManager;

    private List<Grade> currentStudentGrades;

    public List<Grade> getCurrentStudentGrades(){
        if (currentStudentGrades != null) return currentStudentGrades;
        Student currentStudent = studentManager.getCurrentStudent();
        currentStudentGrades = gradeService.getGradesByStudent(currentStudent);
        return currentStudentGrades;
    }

}
