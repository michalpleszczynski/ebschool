package com.ebschool.rest.core.resource;

import com.ebschool.ejb.model.*;
import com.ebschool.ejb.service.TeacherService;
import com.ebschool.ejb.service.UserService;
import com.ebschool.rest.core.model.TeacherElement;
import com.ebschool.rest.core.utils.RestElementBuilder;
import com.ebschool.rest.core.utils.RestHelper;
import com.ebschool.rest.core.utils.paging.PageResult;
import com.ebschool.rest.core.utils.paging.SetPageResult;
import com.ebschool.rest.core.utils.transactions.TransactionRequired;

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
@Path("/teachers")
@RequestScoped
@TransactionRequired
public class TeacherResource {

    @EJB
    UserService userService;

    @EJB
    TeacherService teacherService;

    @Inject
    RestElementBuilder restElementBuilder;

    @GET
    @Path("{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getByLogin(@PathParam("login") String login){
        Teacher teacher = (Teacher) RestHelper.throw404IfNull(userService.getByLogin(login));
        return Response.ok().entity(restElementBuilder.buildTeacherElement(teacher)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll() {
        Set<Teacher> teachers = RestHelper.throw404IfNull(teacherService.getAll());
        PageResult<TeacherElement> results = new SetPageResult<>(
                restElementBuilder.buildElementSet(teachers, TeacherElement.class));
        GenericEntity entities = new GenericEntity<Set<TeacherElement>>((Set<TeacherElement>)results.getPageElements()){};
        return Response.ok().entity(entities).build();
    }

}
