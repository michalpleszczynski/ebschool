package com.ebschool.test.ejb.repo;

import com.ebschool.ejb.model.Level;
import com.ebschool.ejb.model.Student;
import com.ebschool.test.ejb.AbstractArquillianRepositoryTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: michau
 * Date: 5/16/13
 * Time: 8:08 PM
 */
@RunWith(Arquillian.class)
@Transactional(manager = "java:jboss/UserTransaction")
public class LevelRepositoryTest extends AbstractArquillianRepositoryTest {

    @Test
    public void testGetById() throws Exception {
        Level level = levelRepository.getById(1L);
        assertNotNull(level);
        assertEquals("advanced", level.getName());
    }

    @Test
    public void createTest() throws Exception {
        Level level = dataBuilder.buildLevel();
        assertNotNull(level);
        Level returnedLevel = levelRepository.create(level);
        assertEquals(returnedLevel, level);
    }

    @Test
    public void deleteTest() throws Exception {
        Set<Level> levels = levelRepository.getAll();
        assertNotNull(levels);
        assertEquals(3, levels.size());
        levelRepository.delete(levelRepository.getById(2L));
        levels = levelRepository.getAll();
        assertEquals(2, levels.size());
        levelRepository.deleteAll();
        levels = levelRepository.getAll();
        assertEquals(0, levels.size());
    }

}
