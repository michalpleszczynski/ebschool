package com.ebschool.rest.resource;

import com.sun.jersey.api.view.Viewable;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;

/**
 * User: michau
 * Date: 6/6/13
 */
@Path("/session")
@RequestScoped
public class SessionResource {

    @GET
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
