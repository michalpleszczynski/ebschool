package com.ebschool.test.ejb.other;

import com.ebschool.ejb.model.*;
import com.ebschool.test.ejb.AbstractArquillianServiceDataTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: michau
 * Date: 5/19/13
 */
@RunWith(Arquillian.class)
@Transactional(manager = "java:jboss/UserTransaction")
public class EntityRelationshipITest extends AbstractArquillianServiceDataTest {

    @Test
    public void testEntityRelationship() throws Exception {
        // While creating a class with a new not-yet persisted (level,student,teacher and test), those entities
        // should be persisted. We don't have to check for the levels existence before because persisting an already existing
        // entity would throw an exception - no exception == wasn't in db
        ClassInfo newClass = dataBuilder.buildClass();
        Student newStudent = dataBuilder.buildStudent();
        Teacher newTeacher = dataBuilder.buildTeacher();
        StudentTask newStudentTask = dataBuilder.buildStudentTask();

        // level already set in DataBuilder
        newClass.getTeachers().add(newTeacher);
        newClass.getStudents().add(newStudent);
        newClass.getStudentTasks().add(newStudentTask);
        newStudentTask.setClassInfo(newClass);

        // test we will acquire by querying by classInfo
        String levelName = newClass.getLevel().getName();
        String teachersLogin = newTeacher.getLogin();
        String studentsLogin = newStudent.getLogin();

        ClassInfo returedClass = classInfoService.create(newClass);
        assertEquals(newClass, returedClass);

        restartTransaction();

        Teacher savedTeacher = (Teacher)userService.getByLogin(teachersLogin);
        assertNotNull(savedTeacher);
        assertEquals(newTeacher, savedTeacher);

        Student savedStudent = (Student)userService.getByLogin(studentsLogin);
        assertNotNull(savedStudent);
        assertEquals(newStudent, savedStudent);

        Level savedLevel = levelService.getByName(levelName);
        assertNotNull(savedLevel);

        List<StudentTask> savedStudentTasks = studentTaskService.getTestsByClass(classInfoService.getById(returedClass.getId()));
        assertNotNull(savedStudentTasks);
        assertEquals(1, savedStudentTasks.size());
        StudentTask savedStudentTask = savedStudentTasks.get(0);
        assertEquals(newStudentTask, savedStudentTask);
    }

}