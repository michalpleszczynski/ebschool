package com.ebschool.rest.core.resource;

import com.ebschool.model.Grade;
import com.ebschool.rest.core.model.GradeElement;
import com.ebschool.rest.core.utils.RestHelper;
import com.ebschool.rest.core.utils.transactions.TransactionRequired;
import com.ebschool.service.GradeServiceLocal;

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
 * Time: 2:11 PM
 */
@Path("/grades")
@RequestScoped
@TransactionRequired
public class GradeResource {

    @EJB
    GradeServiceLocal gradeService;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getById(@PathParam("id") long id){
        Grade grade = RestHelper.throw404IfNull(gradeService.getById(id));
        GradeElement gradeElement = new GradeElement(grade);
        return Response.ok().entity(gradeElement).build();
    }

}
