package com.ebschool.web.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * User: michau
 * Date: 9/4/13
 */
@ManagedBean
@RequestScoped
public class RedirectBean implements Serializable {

    public void redirect(String toURL){
        FacesContext.getCurrentInstance();
    }

}
