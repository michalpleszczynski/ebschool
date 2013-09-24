package com.ebschool.test.ejb;

import com.ebschool.ejb.exception.DuplicatedUserException;
import com.ebschool.ejb.model.User;
import com.ebschool.ejb.model.time.ClassTime;
import com.ebschool.ejb.repo.UserRepository;
import com.ebschool.ejb.security.Roles;
import com.ebschool.ejb.service.*;
import com.ebschool.ejb.utils.Identifiable;
import com.ebschool.test.ejb.utils.DataBuilder;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;
import java.io.File;

/**
 * User: michau
 * Date: 9/22/13
 */
@RunWith(Arquillian.class)
@Transactional(manager = "java:jboss/UserTransaction")
public abstract class AbstractArquillianServiceTest extends AbstractArquillianTest {

    @EJB
    protected UserService userService;

    @EJB
    protected ParentService parentService;

    @EJB
    protected TeacherService teacherService;

    @EJB
    protected StudentService studentService;

    @EJB
    protected LevelService levelService;

    @EJB
    protected ClassInfoService classInfoService;

    @EJB
    protected GradeService gradeService;

    @EJB
    protected StudentTaskService studentTaskService;

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
                .resolve("joda-time:joda-time", "joda-time:joda-time-hibernate", "org.jadira.usertype:usertype.jodatime",
                        "org.codehaus.groovy:groovy-all", "mysql:mysql-connector-java").withTransitivity().asFile();

        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Identifiable.class.getPackage())
                .addPackage(User.class.getPackage())
                .addPackage(ClassTime.class.getPackage())
                .addPackage(Roles.class.getPackage())
                .addPackage(UserRepository.class.getPackage())
                .addPackage(UserService.class.getPackage())
                .addPackage(DataBuilder.class.getPackage())
                .addPackage(DuplicatedUserException.class.getPackage())
                .addPackage(AbstractArquillianServiceDataTest.class.getPackage())
                .addAsLibraries(libs)
                .addAsResource(EmptyAsset.INSTANCE, "META-INF/beans.xml")
                .addAsResource("test-persistence.xml","META-INF/persistence.xml")
                .addAsResource("test-hibernate.cfg.xml");

//        System.out.println(war.toString(true));

        return war;
    }

    @Override
    protected void executeScripts() {
        sqlExecutor.invokeMethod("cleanDB", new Object[]{});
        sqlExecutor.invokeMethod("executeSqlScript", new Object[]{new File("src/test/resources/sql-scripts/schema.sql")});
        sqlExecutor.invokeMethod("executeSqlScript", new Object[]{new File("src/test/resources/sql-scripts/roles.sql")});
    }

}
