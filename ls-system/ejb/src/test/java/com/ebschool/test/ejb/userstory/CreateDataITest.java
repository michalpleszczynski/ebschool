package com.ebschool.test.ejb.userstory;

import com.ebschool.ejb.model.Parent;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.model.Teacher;
import com.ebschool.ejb.model.User;
import com.ebschool.test.ejb.AbstractArquillianTest;
import com.ebschool.test.ejb.utils.DataBuilder;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
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
@TransactionManagement(TransactionManagementType.BEAN)
@Transactional(manager = "java:jboss/UserTransaction")
public class CreateDataITest extends AbstractArquillianTest {

    @Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql"})
    public void createUsers() throws Exception {

        Student newStudent1 = DataBuilder.buildStudent();
        Student newStudent2 = DataBuilder.buildStudent();
        Student newStudent3 = DataBuilder.buildStudent();
        Parent newParent1 = DataBuilder.buildParent();
        Parent newParent2 = DataBuilder.buildParent();
        Teacher newTeacher1 = DataBuilder.buildTeacher();

        newStudent1 = studentService.create(newStudent1);
        newStudent2 = studentService.create(newStudent2);
        newStudent3 = studentService.create(newStudent3);
        newParent1 = parentService.create(newParent1);
        newParent2 = parentService.create(newParent2);
        newTeacher1 = teacherService.create(newTeacher1);

        restartTransaction();

        Set<User> allUsers = userService.getAll(User.class);
        assertNotNull(allUsers);
        assertEquals(6, allUsers.size());
    }

}
