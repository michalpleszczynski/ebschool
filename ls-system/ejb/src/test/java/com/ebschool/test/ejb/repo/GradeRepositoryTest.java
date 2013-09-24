package com.ebschool.test.ejb.repo;

import com.ebschool.ejb.model.Grade;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.model.StudentTask;
import com.ebschool.test.ejb.AbstractArquillianRepositoryTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 5/16/13
 * Time: 6:28 PM
 */
@RunWith(Arquillian.class)
@Transactional(manager = "java:jboss/UserTransaction")
public class GradeRepositoryTest extends AbstractArquillianRepositoryTest {

    @Test
    public void getByIdTest() throws Exception {
        Grade grade = gradeRepository.getById(1L);
        assertNotNull(grade);
        assertEquals("this is a comment", grade.getComment());
        assertEquals(76, grade.getPercentage());
        assertEquals(3, grade.getWeight());
        Student student = grade.getStudent();
        assertNotNull(student);
        assertEquals(new Long(1L), student.getId());
        StudentTask studentTask = grade.getStudentTask();
        assertNotNull(studentTask);
        assertEquals(new Long(1L), studentTask.getId());
    }

    @Test
    public void createTest() throws Exception {
        Grade grade = dataBuilder.buildGrade();
        assertNotNull(grade);
        Student student = userRepository.getById(1L);
        assertNotNull(student);
        grade.setStudent(student);
        Grade returnedGrade = gradeRepository.create(grade);
        assertEquals(returnedGrade, grade);
    }

    @Test
    public void deleteTest() throws Exception {
        Set<Grade> grades = gradeRepository.getAll();
        assertNotNull(grades);
        assertEquals(6, grades.size());
        Iterator iterator = grades.iterator();
        Grade grade1 = (Grade)iterator.next();
        Grade grade2 = (Grade)iterator.next();
        gradeRepository.delete(grade1, grade2);
        grades = gradeRepository.getAll();
        assertEquals(4, grades.size());
        gradeRepository.deleteAll();
        grades = gradeRepository.getAll();
        assertEquals(0, grades.size());
    }
}
