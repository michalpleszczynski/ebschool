package com.ebschool.test.ejb;

import com.ebschool.ejb.exception.DuplicatedUserException;
import com.ebschool.ejb.model.User;
import com.ebschool.ejb.model.time.ClassTime;
import com.ebschool.ejb.repo.UserRepository;
import com.ebschool.ejb.security.Roles;
import com.ebschool.ejb.service.*;
import com.ebschool.ejb.utils.Identifiable;
import com.ebschool.test.ejb.utils.DataBuilder;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
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
 * Date: 9/7/13
 */
@RunWith(Arquillian.class)
@Transactional(manager = "java:jboss/UserTransaction")
public abstract class AbstractArquillianServiceDataTest extends AbstractArquillianServiceTest{

    @Override
    protected void executeScripts() {
        sqlExecutor.invokeMethod("cleanDB", new Object[]{});
        sqlExecutor.invokeMethod("executeSqlScript", new Object[]{new File("src/test/resources/sql-scripts/schema.sql")});
        sqlExecutor.invokeMethod("executeSqlScript", new Object[]{new File("src/test/resources/sql-scripts/roles.sql")});
        sqlExecutor.invokeMethod("executeSqlScript", new Object[]{new File("src/test/resources/datasets/mysql-big-dataset.sql")});
    }

}
