package com.ebschool.rest.core.resource;

import com.ebschool.ejb.model.Level;
import com.ebschool.rest.core.model.LevelElement;
import com.ebschool.rest.core.utils.RestHelper;
import com.ebschool.rest.core.utils.transactions.TransactionRequired;
import com.ebschool.ejb.service.LevelServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getByName(@PathParam("name") String name) {
        Level level = RestHelper.throw404IfNull(levelService.getByName(name));
        LevelElement levelElement = new LevelElement(level);
        return Response.ok().entity(levelElement).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
