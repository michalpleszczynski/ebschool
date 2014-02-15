package com.ebschool.test.ejb.userstory;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Grade;
import com.ebschool.ejb.model.Level;
import com.ebschool.ejb.model.Student;
import com.ebschool.test.ejb.AbstractArquillianServiceDataTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 5/19/13
 */
@RunWith(Arquillian.class)
@TransactionManagement(TransactionManagementType.BEAN)
@Transactional(manager = "java:jboss/UserTransaction")
public class StudentIServiceTest extends AbstractArquillianServiceDataTest {

    @Test
    public void testStudentLifecycle() throws Exception {

        // Student enrolls to school
        Student newStudent = dataBuilder.buildStudent();
        newStudent = (Student)userService.create(newStudent);
        Level level = levelService.getByName("elementary");
        newStudent.setLevel(level);
        classInfoService.addStudents(classInfoService.getById(1L), newStudent);

        // Participates in classes, gets grades
        gradeService.giveGrade(newStudent, dataBuilder.buildGrade());
        Grade grade2 = new Grade();
        grade2.setComment("second grade");
        grade2.setPercentage((byte) 99);
        grade2.setWeight((byte) 2);
        gradeService.giveGrade(newStudent, grade2);

        restartTransaction();

        // check if the changes were saved properly
        Student savedStudent = (Student)userService.getByLogin(dataBuilder.getLastCreatedStudent().getLogin());
        assertNotNull(savedStudent);
        assertEquals(newStudent, savedStudent);

        Level returnedLevel = savedStudent.getLevel();
        assertNotNull(returnedLevel);
        assertEquals("elementary", returnedLevel.getName());

        Set<ClassInfo> savedClasses = savedStudent.getClasses();
        assertNotNull(savedClasses);
        assertEquals(1, savedClasses.size());
        assertEquals(new Long(1L), savedClasses.iterator().next().getId());

        List<Student> studentsOfClass = studentService.getByClass(classInfoService.getById(1L));
        assertNotNull(studentsOfClass);
        assertTrue(studentsOfClass.contains(savedStudent));

        List<Grade> savedGrades = gradeService.getGradesByStudent(savedStudent);
        assertNotNull(savedGrades);
        assertEquals(2, savedGrades.size());
        Grade grade1 = savedGrades.get(0);
        assertEquals(grade1.getComment(), dataBuilder.getLastCreatedGrade().getComment());
        assertEquals(grade1.getPercentage(), dataBuilder.getLastCreatedGrade().getPercentage());
        assertEquals(grade1.getWeight(), dataBuilder.getLastCreatedGrade().getWeight());
        grade2 = savedGrades.get(1);
        assertEquals(grade2.getComment(), "second grade");
        assertEquals(grade2.getPercentage(), (byte)99);
        assertEquals(grade2.getWeight(), (byte)2);

        // Student finished his school, his account first is disabled
        userService.disableAccount(savedStudent);
        assertFalse(savedStudent.isActive());

        // and then after sometime deleted completely
        userService.delete(savedStudent);

        restartTransaction();

        // check if student was properly deleted along with his grades and from his classes
        Student deletedStudent = (Student)userService.getByLogin(dataBuilder.getLastCreatedStudent().getLogin());
        assertNull(deletedStudent);

        assertNull(gradeService.getById(grade1.getId()));
        assertNull(gradeService.getById(grade2.getId()));

        studentsOfClass = studentService.getByClass(classInfoService.getById(1L));
        assertNotNull(studentsOfClass);
        assertFalse(studentsOfClass.contains(savedStudent));
    }

}
