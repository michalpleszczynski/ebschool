package com.ebschool.web.beans.producers;

import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * User: michau
 * Date: 1/23/14
 * Time: 11:22 PM
 */
public class Resources {

    @Produces
    FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Produces
    ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

}
