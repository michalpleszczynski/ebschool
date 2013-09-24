package com.ebschool.rest.core.resource;

import com.ebschool.ejb.model.StudentTask;
import com.ebschool.ejb.service.StudentTaskService;
import com.ebschool.rest.core.model.TestElement;
import com.ebschool.rest.core.utils.RestHelper;
import com.ebschool.rest.core.utils.transactions.TransactionRequired;

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
    StudentTaskService studentTaskService;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getById(@PathParam("id") long id){
        StudentTask studentTask = RestHelper.throw404IfNull(studentTaskService.getById(id));
        TestElement testElement = new TestElement(studentTask);
        return Response.ok().entity(testElement).build();
    }

}
