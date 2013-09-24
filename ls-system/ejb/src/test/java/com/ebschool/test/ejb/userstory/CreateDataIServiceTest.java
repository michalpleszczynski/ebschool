package com.ebschool.test.ejb.userstory;

import com.ebschool.ejb.model.*;
import com.ebschool.test.ejb.AbstractArquillianServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.runner.RunWith;
import org.junit.Test;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 9/7/13
 */
@RunWith(Arquillian.class)
@Transactional(manager = "java:jboss/UserTransaction")
public class CreateDataIServiceTest extends AbstractArquillianServiceTest {

    @Test
//    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql"})
    public void createUsers() throws Exception {

        Student newStudent1 = dataBuilder.buildStudent();
        Student newStudent2 = dataBuilder.buildStudent();
        Student newStudent3 = dataBuilder.buildStudent();
        Parent newParent1 = dataBuilder.buildParent();
        Parent newParent2 = dataBuilder.buildParent();
        Teacher newTeacher1 = dataBuilder.buildTeacher();

        newStudent1 = studentService.create(newStudent1);
        newStudent2 = studentService.create(newStudent2);
        newStudent3 = studentService.create(newStudent3);
        newParent1 = parentService.create(newParent1);
        newParent2 = parentService.create(newParent2);
        newTeacher1 = teacherService.create(newTeacher1);

        parentService.addChildrenAccounts(newParent1, newStudent1, newStudent2);

        Level level1 = dataBuilder.buildLevel("elementary");
        Level level2 = dataBuilder.buildLevel("intermediate");
        Level level3 = dataBuilder.buildLevel("advanced");

        level1 = levelService.create(level1);
        level2 = levelService.create(level2);
        level3 = levelService.create(level3);

        newStudent1.setLevel(level1);
        newStudent2.setLevel(level2);
        newStudent3.setLevel(level2);

        ClassInfo class1 = dataBuilder.buildClass("first class");
        ClassInfo class2 = dataBuilder.buildClass("second class");

        class1.setLevel(level1);
        class2.setLevel(level2);

        class1 = classInfoService.create(class1);
        class2 = classInfoService.create(class2);

        classInfoService.addTeachers(class1, newTeacher1);
        classInfoService.addTeachers(class2, newTeacher1);

        classInfoService.addStudents(class1, newStudent1, newStudent2);
        classInfoService.addStudents(class2, newStudent2, newStudent3);

        StudentTask assignment = dataBuilder.buildStudentTask("first assignment", StudentTask.TaskType.ASSIGNMENT);
        StudentTask test = dataBuilder.buildStudentTask();

        classInfoService.addStudentTasks(class1, assignment, test);

        restartTransaction();

        Set<User> allUsers = userService.getAll(User.class);
        assertNotNull(allUsers);
        assertEquals(6, allUsers.size());
    }

}
