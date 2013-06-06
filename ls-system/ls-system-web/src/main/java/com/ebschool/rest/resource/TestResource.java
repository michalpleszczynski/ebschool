package com.ebschool.rest.resource;

import com.ebschool.model.Test;
import com.ebschool.rest.model.TestElement;
import com.ebschool.rest.utils.RestHelper;
import com.ebschool.rest.utils.transactions.TransactionRequired;
import com.ebschool.service.TestServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Author: mpleszczynski
 * Date: 6/6/13
 * Time: 2:08 PM
 */
@Path("/tests")
@RequestScoped
@TransactionRequired
public class TestResource {

    @EJB
    TestServiceLocal testService;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getById(@PathParam("id") long id){
        Test test = RestHelper.throw404IfNull(testService.getById(id));
        TestElement testElement = new TestElement(test);
        return Response.ok().entity(testElement).build();
    }

}
