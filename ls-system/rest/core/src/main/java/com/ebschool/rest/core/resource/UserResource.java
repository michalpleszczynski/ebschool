package com.ebschool.rest.core.resource;

import com.ebschool.ejb.model.User;
import com.ebschool.ejb.service.UserService;
import com.ebschool.rest.core.utils.RestElementBuilder;
import com.ebschool.rest.core.utils.RestHelper;
import com.ebschool.rest.core.utils.transactions.TransactionRequired;

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
    UserService userService;

    @Inject
    RestElementBuilder restElementBuilder;

    @GET
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getById(@PathParam("id") long id){
        User user = RestHelper.throw404IfNull(userService.getById(id),
                String.format("User with login %d cannot be found", id));
        return Response.ok().entity(restElementBuilder.buildUserElement(user)).build();
    }

}
