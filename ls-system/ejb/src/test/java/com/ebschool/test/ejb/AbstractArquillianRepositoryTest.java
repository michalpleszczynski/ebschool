package com.ebschool.test.ejb;

import com.ebschool.ejb.model.User;
import com.ebschool.ejb.model.time.ClassTime;
import com.ebschool.ejb.repo.*;
import com.ebschool.ejb.security.Roles;
import com.ebschool.ejb.utils.Identifiable;
import com.ebschool.test.ejb.utils.DataBuilder;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

/**
 * User: michau
 * Date: 9/20/13
 */
@RunWith(Arquillian.class)
@Transactional(manager = "java:jboss/UserTransaction")
public abstract class AbstractArquillianRepositoryTest extends AbstractArquillianTest{

    @Inject
    protected ClassInfoRepository classInfoRepository;

    @Inject
    protected GradeRepository gradeRepository;

    @Inject
    protected UserRepository userRepository;

    @Inject
    protected StudentTaskRepository studentTaskRepository;

    @Inject
    protected LevelRepository levelRepository;

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
                .addPackage(DataBuilder.class.getPackage())
                .addClass(AbstractArquillianRepositoryTest.class)
                .addClass(AbstractArquillianTest.class)
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
        sqlExecutor.invokeMethod("executeSqlScript", new Object[]{new File("src/test/resources/datasets/mysql-big-dataset.sql")});
    }

}
