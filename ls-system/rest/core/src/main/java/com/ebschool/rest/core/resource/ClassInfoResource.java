package com.ebschool.rest.core.resource;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.service.ClassInfoService;
import com.ebschool.rest.core.model.ClassInfoElement;
import com.ebschool.rest.core.utils.RestElementBuilder;
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
@Path("/classes")
@RequestScoped
@TransactionRequired
public class ClassInfoResource {

    @EJB
    ClassInfoService classInfoService;

    @Inject
    RestElementBuilder restElementBuilder;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll(){
        Set<ClassInfo> classes = classInfoService.getAll();
        PageResult<ClassInfoElement> classElements = new SetPageResult<>(
                restElementBuilder.buildElementSet(classes, ClassInfoElement.class));
        GenericEntity entity = new GenericEntity<Set<ClassInfoElement>>((Set<ClassInfoElement>)classElements.getPageElements()){};
        return Response.ok().entity(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getById(@PathParam("id") long id){
        ClassInfo classInfo = classInfoService.getById(id);
        return Response.ok().entity(restElementBuilder.buildClassInfoElement(classInfo)).build();
    }

}
