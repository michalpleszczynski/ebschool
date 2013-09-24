package com.ebschool.rest.core;

import com.ebschool.rest.core.resource.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * A class extending {@link Application} and annotated with @ApplicationPath is the Java EE 6
 * "no XML" approach to activating JAX-RS.
 * <p/>
 * <p>
 * Resources are served relative to the servlet path specified in the {@link ApplicationPath}
 * annotation.
 * </p>
 */
@ApplicationPath("/resource")
public class JaxRsActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet();
        s.add(ClassInfoResource.class);
        s.add(GradeResource.class);
        s.add(LevelResource.class);
        s.add(ParentResource.class);
        s.add(SessionResource.class);
        s.add(StudentResource.class);
        s.add(TeacherResource.class);
        s.add(StudentTaskResource.class);
        s.add(UserResource.class);
        return s;
    }

}
