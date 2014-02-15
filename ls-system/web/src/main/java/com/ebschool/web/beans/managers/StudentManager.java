package com.ebschool.web.beans.managers;

import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.service.StudentService;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * User: michau
 * Date: 1/19/14
 * Time: 3:45 PM
 */
@Named
@SessionScoped
@Stateful
public class StudentManager implements Serializable {

    // ExternalContext is not serializable so it have to be transient
    @Inject
    private transient ExternalContext externalContext;

    private Student currentStudent;

    @EJB
    private StudentService studentService;

    public Student getCurrentStudent(){
        if (currentStudent != null) return currentStudent;
        String username = externalContext.getRemoteUser();
        currentStudent = studentService.getByLogin(username, Student.Related.DETAILED_INFO, Student.Related.LEVEL);
        return currentStudent;
    }

}
