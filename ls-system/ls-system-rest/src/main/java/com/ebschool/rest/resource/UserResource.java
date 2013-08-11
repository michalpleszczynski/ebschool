package com.ebschool.rest.resource;

import com.ebschool.model.Parent;
import com.ebschool.model.Student;
import com.ebschool.model.Teacher;
import com.ebschool.model.User;
import com.ebschool.rest.utils.RestElementBuilder;
import com.ebschool.rest.utils.RestHelper;
import com.ebschool.rest.utils.transactions.TransactionRequired;
import com.ebschool.service.UserServiceLocal;
import org.jboss.logging.Param;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;

/**
 * User: michau
 * Date: 6/2/13
 */
@Path("/users")
@RequestScoped
@TransactionRequired
public class UserResource {

    @EJB
    UserServiceLocal userService;

    @Inject
    RestElementBuilder restElementBuilder;

    @GET
    @Path("{current}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getLoggedUser(@Context HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String login = principal.getName();
        User user = RestHelper.throw404IfNull(userService.getByLogin(login),
                String.format("User with login %s cannot be found", login));
        return buildUserResponse(user);
    }

    @GET
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getById(@PathParam("id") long id){
        User user = RestHelper.throw404IfNull(userService.getById(id),
                String.format("User with login %d cannot be found", id));
        return buildUserResponse(user);
    }

    private Response buildUserResponse(User user){
        if (Student.class.isAssignableFrom(user.getClass())){
            return Response.ok().entity(restElementBuilder.buildStudentElement((Student)user)).build();
        } else if (Teacher.class.isAssignableFrom(user.getClass())){
            return Response.ok().entity(restElementBuilder.buildTeacherElement((Teacher)user)).build();
        } else if (Parent.class.isAssignableFrom(user.getClass())){
            return Response.ok().entity(restElementBuilder.buildParentElement((Parent)user)).build();
        }
        return Response.noContent().build();
    }

}
