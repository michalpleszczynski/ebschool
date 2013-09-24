package com.ebschool.test.ejb.repo;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.test.ejb.AbstractArquillianRepositoryTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 5/13/13
 * Time: 2:58 PM
 */
@RunWith(Arquillian.class)
@Transactional(manager = "java:jboss/UserTransaction")
public class ClassInfoRepositoryTest extends AbstractArquillianRepositoryTest {

    @Test
    public void getByIdTest() throws Exception {
        ClassInfo classInfo = classInfoRepository.getById(1L);
        assertNotNull(classInfo);
        assertEquals("this is a description", classInfo.getDescription());
    }

    @Test
    public void createTest() throws Exception {
        ClassInfo classInfo = dataBuilder.buildClass();
        assertNotNull(classInfo);
        ClassInfo returnedClass = classInfoRepository.create(classInfo);
        assertEquals(returnedClass, classInfo);
    }

    @Test
    public void deleteTest() throws Exception {
        Set<ClassInfo> classes = classInfoRepository.getAll();
        assertNotNull(classes);
        assertEquals(3, classes.size());
        ClassInfo classInfo = classInfoRepository.getById(3L);
        classInfoRepository.delete(classInfo);
        classes = classInfoRepository.getAll();
        assertNotNull(classes);
        assertEquals(2, classes.size());
        classInfoRepository.deleteAll();
        classes = classInfoRepository.getAll();
        assertNotNull(classes);
        assertTrue(classes.isEmpty());
    }
}
