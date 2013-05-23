package com.ebschool.rest;

import java.util.Hashtable;
import java.util.Set;

//import javax.ejb.EJB;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ebschool.model.User;
import com.ebschool.service.UserServiceImpl;
import com.ebschool.service.UserServiceRemote;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/members")
@RequestScoped
public class MemberResourceRESTService {

    @EJB
    UserServiceRemote userServiceRemote;

    public MemberResourceRESTService(){
//        try {
//            userServiceRemote = lookupRemoteStatelessBean();
//            if (userServiceRemote == null){
//                System.out.println("NULLLLLLLLLLLL!");
//            }
//        } catch (NamingException ex){
//            ex.printStackTrace();
//        }
    }

    private UserServiceRemote lookupRemoteStatelessBean() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        // The app name is the application name of the deployed EJBs. This is typically the ear name
        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
        // EJB deployment on the server.
        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
        final String appName = "ls-system";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "ls-system-ejb-1.0-SNAPSHOT";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = UserServiceImpl.class.getSimpleName();
        // the remote view fully qualified class name
        final String viewClassName = UserServiceRemote.class.getName();
        // let's do the lookup
        String lookupString = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName;
        return (UserServiceRemote) context.lookup(lookupString);
    }

    @GET
    @Produces("text/xml")
    public Set<User> listAllUsers() {
        Set<User> allUsers = userServiceRemote.getAllUsers();
        return allUsers;
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("text/xml")
    public User lookupMemberById(@PathParam("id") long id) {
        return userServiceRemote.getById(id);
    }
}
