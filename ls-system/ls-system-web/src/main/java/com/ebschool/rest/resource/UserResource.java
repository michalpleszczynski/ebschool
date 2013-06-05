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

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Path("{login}/{password}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getByLogin(@PathParam("login") String login,
                                @PathParam("password") String password){
        User user = RestHelper.throw404IfNull(userService.getByLogin(login),
                String.format("User with login %s cannot be found", login));
        if (!password.equals(user.getPassword())){
            return Response.ok("Password doesn't match", MediaType.TEXT_PLAIN_TYPE).build();
        }
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
