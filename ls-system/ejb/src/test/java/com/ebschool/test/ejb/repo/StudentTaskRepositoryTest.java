package com.ebschool.test.ejb.repo;

import com.ebschool.ejb.model.ClassInfo;
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
 * Author: mpleszczynski
 * Date: 5/17/13
 * Time: 1:23 PM
 */
@RunWith(Arquillian.class)
@Transactional(manager = "java:jboss/UserTransaction")
public class StudentTaskRepositoryTest extends AbstractArquillianRepositoryTest {

    @Test
    public void testGetById() throws Exception {
        StudentTask studentTask = studentTaskRepository.getById(1L);
        assertNotNull(studentTask);
        assertEquals("this is a test", studentTask.getDescription());
        ClassInfo testClass = studentTask.getClassInfo();
        testClass = classInfoRepository.getById(testClass.getId());
        assertNotNull(testClass);
        assertEquals(new Long(1L), testClass.getId());
    }

    @Test
    public void testCreate() throws Exception {
        StudentTask studentTask = dataBuilder.buildStudentTask();
        ClassInfo classInfo = classInfoRepository.getById(1L);
        studentTask.setClassInfo(classInfo);
        StudentTask returnedStudentTask = studentTaskRepository.create(studentTask);
        assertEquals(returnedStudentTask, studentTask);
    }

    @Test
    public void testDelete() throws Exception {
        Set<StudentTask> studentTasks = studentTaskRepository.getAll();
        assertNotNull(studentTasks);
        assertEquals(4, studentTasks.size());
        Iterator iterator = studentTasks.iterator();
        StudentTask studentTask1 = (StudentTask)iterator.next();
        StudentTask studentTask2 = (StudentTask)iterator.next();
        studentTaskRepository.delete(studentTask1, studentTask2);
        studentTasks = studentTaskRepository.getAll();
        assertEquals(2, studentTasks.size());
        studentTaskRepository.deleteAll();
        studentTasks = studentTaskRepository.getAll();
        assertEquals(0, studentTasks.size());
    }

}
