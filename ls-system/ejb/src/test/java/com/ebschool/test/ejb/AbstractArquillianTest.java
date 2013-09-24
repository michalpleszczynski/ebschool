package com.ebschool.test.ejb;

import com.ebschool.test.ejb.utils.DataBuilder;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import java.io.File;

/**
 * User: michau
 * Date: 9/24/13
 */
@RunWith(Arquillian.class)
@Transactional(manager = "java:jboss/UserTransaction")
public abstract class AbstractArquillianTest {

    protected static GroovyObject sqlExecutor;

    protected static DataBuilder dataBuilder = new DataBuilder();

    @Resource(lookup = "java:jboss/UserTransaction")
    protected UserTransaction userTransaction;

    protected abstract void executeScripts();

    @Before
    public void reloadData() throws Exception {
        dataBuilder.clear();
        // configure groovy sqlExecutor
        ClassLoader parent = this.getClass().getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        Class groovyClass = loader.parseClass(new File("src/test/resources/groovy/scriptExecutor.groovy"));
        sqlExecutor = (GroovyObject) groovyClass.newInstance();
        Object[] args = {new File("src/test/resources/test-hibernate.cfg.xml")};
        sqlExecutor.invokeMethod("configure", args);

        executeScripts();
    }

    protected void restartTransaction() throws Exception {
        userTransaction.commit();
        userTransaction.begin();
    }


}
