package com.ebschool.rest.core.resource;

import com.ebschool.ejb.model.User;
import com.ebschool.rest.core.utils.RestElementBuilder;
import com.ebschool.rest.core.utils.RestHelper;
import com.ebschool.rest.core.utils.transactions.TransactionRequired;
import com.ebschool.ejb.service.UserServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.Principal;

/**
 * User: michau
 * Date: 6/6/13
 */
@Path("/session")
@RequestScoped
@TransactionRequired
public class SessionResource {

    @EJB
    UserServiceLocal userService;

    @Inject
    RestElementBuilder restElementBuilder;

    @GET
    @Path("/currentUser")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getLoggedUser(@Context HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String login = principal.getName();
        User user = RestHelper.throw404IfNull(userService.getByLogin(login),
                String.format("User with login %s cannot be found", login));
        return Response.ok().entity(restElementBuilder.buildUserElement(user)).build();
    }


    @POST
    @Path("/logout")
    public Response logout(@Context HttpServletRequest request)
        throws IOException {
        HttpSession session = request.getSession();
        if (session != null){
            session.invalidate();
        }
        return Response.noContent().build();
    }

}
