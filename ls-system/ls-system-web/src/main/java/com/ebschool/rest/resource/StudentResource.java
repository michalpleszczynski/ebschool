package com.ebschool.rest.resource;

import com.ebschool.model.Student;
import com.ebschool.rest.model.StudentElement;
import com.ebschool.rest.utils.RestElementBuilder;
import com.ebschool.rest.utils.RestHelper;
import com.ebschool.rest.utils.paging.PageResult;
import com.ebschool.rest.utils.paging.SetPageResult;
import com.ebschool.rest.utils.transactions.TransactionRequired;
import com.ebschool.service.StudentServiceLocal;
import com.ebschool.service.UserServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@Path("/students")
@RequestScoped
@TransactionRequired
public class StudentResource {

    @EJB
    UserServiceLocal userService;

    @EJB
    StudentServiceLocal studentService;

    @Inject
    RestElementBuilder restElementBuilder;

    public StudentResource() {}

    @GET
    @Path("{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getByLogin(@PathParam("login") String login){
        Student student = (Student) RestHelper.throw404IfNull(userService.getByLogin(login));
        return Response.ok().entity(restElementBuilder.buildStudentElement(student)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        Set<Student> students = RestHelper.throw404IfNull(studentService.getAll());
        PageResult<StudentElement> results = new SetPageResult<>(
                restElementBuilder.buildElementSet(students, StudentElement.class));
        GenericEntity entities = new GenericEntity<Set<StudentElement>>((Set<StudentElement>)results.getPageElements()){};
        return Response.ok().entity(entities).build();
    }

}
