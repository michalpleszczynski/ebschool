package com.ebschool.rest.resource;

import com.ebschool.model.*;
import com.ebschool.rest.model.StudentElement;
import com.ebschool.rest.model.TeacherElement;
import com.ebschool.rest.utils.RestElementBuilder;
import com.ebschool.rest.utils.RestHelper;
import com.ebschool.rest.utils.paging.PageResult;
import com.ebschool.rest.utils.paging.SetPageResult;
import com.ebschool.rest.utils.transactions.TransactionRequired;
import com.ebschool.service.ParentServiceLocal;
import com.ebschool.service.TeacherServiceLocal;
import com.ebschool.service.UserServiceLocal;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@Path("/teachers")
@RequestScoped
@TransactionRequired
public class TeacherResource {

    @EJB
    UserServiceLocal userService;

    @EJB
    TeacherServiceLocal teacherService;

    @Inject
    RestElementBuilder restElementBuilder;

    @GET
    @Path("{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getByLogin(@PathParam("login") String login){
        Teacher teacher = (Teacher) RestHelper.throw404IfNull(userService.getByLogin(login));
        return Response.ok().entity(restElementBuilder.buildTeacherElement(teacher)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        Set<Teacher> teachers = RestHelper.throw404IfNull(teacherService.getAll());
        PageResult<TeacherElement> results = new SetPageResult<>(
                restElementBuilder.buildElementSet(teachers, TeacherElement.class));
        GenericEntity entities = new GenericEntity<Set<TeacherElement>>((Set<TeacherElement>)results.getPageElements()){};
        return Response.ok().entity(entities).build();
    }

}
