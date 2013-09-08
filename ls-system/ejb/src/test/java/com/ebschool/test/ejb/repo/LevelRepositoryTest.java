package com.ebschool.test.ejb.repo;

import com.ebschool.ejb.model.Grade;
import com.ebschool.ejb.model.Level;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.repo.ClassInfoRepository;
import com.ebschool.ejb.repo.GradeRepository;
import com.ebschool.ejb.repo.LevelRepository;
import com.ebschool.ejb.repo.UserRepository;
import com.ebschool.ejb.security.Roles;
import com.ebschool.ejb.utils.Identifiable;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import com.ebschool.test.ejb.utils.DataBuilder;

import javax.inject.Inject;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 5/16/13
 * Time: 8:08 PM
 */
@RunWith(Arquillian.class)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
@Transactional(manager = "java:jboss/UserTransaction")
public class LevelRepositoryTest {

    @Inject
    LevelRepository levelRepository;

    @Inject
    ClassInfoRepository classInfoRepository;

    @Inject
    UserRepository userRepository;

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Identifiable.class.getPackage())
                .addPackage(Grade.class.getPackage())
                .addPackage(Roles.class.getPackage())
                .addPackage(GradeRepository.class.getPackage())
                .addPackage(DataBuilder.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsResource("test-hibernate.cfg.xml");

        return ejb;
    }

    @org.junit.Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void testGetById() throws Exception {
        Level level = levelRepository.getById(1L);
        assertNotNull(level);
        assertEquals("advanced", level.getName());
    }

    @org.junit.Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void createTest() throws Exception {
        Level level = DataBuilder.buildLevel();
        assertNotNull(level);
        Level returnedLevel = levelRepository.create(level);
        assertEquals(returnedLevel, level);
    }

    @org.junit.Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-big-dataset.sql"})
    public void deleteTest() throws Exception {
        Set<Level> levels = levelRepository.getAll();
        assertNotNull(levels);
        assertEquals(3, levels.size());
        classInfoRepository.deleteAll();
        userRepository.deleteAll(Student.class);
        levelRepository.delete(levelRepository.getById(2L));
        levels = levelRepository.getAll();
        assertEquals(2, levels.size());
        levelRepository.deleteAll();
        levels = levelRepository.getAll();
        assertEquals(0, levels.size());
    }

}
