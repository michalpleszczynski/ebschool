package com.ebschool.rest.core.resource;

import com.ebschool.model.Parent;
import com.ebschool.rest.core.model.ParentElement;
import com.ebschool.rest.core.utils.RestElementBuilder;
import com.ebschool.rest.core.utils.RestHelper;
import com.ebschool.rest.core.utils.paging.PageResult;
import com.ebschool.rest.core.utils.paging.SetPageResult;
import com.ebschool.rest.core.utils.transactions.TransactionRequired;
import com.ebschool.service.ParentServiceLocal;
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
@Path("/parents")
@RequestScoped
@TransactionRequired
public class ParentResource {

    @EJB
    UserServiceLocal userService;

    @EJB
    ParentServiceLocal parentService;

    @Inject
    RestElementBuilder restElementBuilder;

    @GET
    @Path("{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getByLogin(@PathParam("login") String login){
        Parent parent = (Parent) RestHelper.throw404IfNull(userService.getByLogin(login));
        return Response.ok().entity(restElementBuilder.buildParentElement(parent)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll() {
        Set<Parent> parents = RestHelper.throw404IfNull(parentService.getAll());
        PageResult<ParentElement> results = new SetPageResult<>(
                restElementBuilder.buildElementSet(parents, ParentElement.class));
        GenericEntity entities = new GenericEntity<Set<ParentElement>>((Set<ParentElement>)results.getPageElements()){};
        return Response.ok().entity(entities).build();
    }

}
