package com.ebschool.rest.resource;

import com.ebschool.model.Level;
import com.ebschool.rest.model.LevelElement;
import com.ebschool.rest.utils.RestElementBuilder;
import com.ebschool.rest.utils.RestHelper;
import com.ebschool.rest.utils.paging.PageResult;
import com.ebschool.rest.utils.paging.SetPageResult;
import com.ebschool.rest.utils.transactions.TransactionRequired;
import com.ebschool.service.LevelServiceLocal;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@Path("/levels")
@RequestScoped
@TransactionRequired
public class LevelResource {

    @EJB
    LevelServiceLocal levelService;

    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getByName(@PathParam("name") String name) {
        Level level = RestHelper.throw404IfNull(levelService.getByName(name));
        LevelElement levelElement = new LevelElement(level);
        return Response.ok().entity(levelElement).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        Set<Level> levels = RestHelper.throw404IfNull(levelService.getAll());
        Set<LevelElement> levelElements = new HashSet<>();
        for (Level level : levels){
            levelElements.add(new LevelElement(level));
        }
        GenericEntity entities = new GenericEntity<Set<LevelElement>>(levelElements){};
        return Response.ok().entity(entities).build();
    }

}
