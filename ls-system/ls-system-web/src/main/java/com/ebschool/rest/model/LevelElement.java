package com.ebschool.rest.model;

import com.ebschool.model.Level;
import com.ebschool.rest.ResponseEntityBean;
import com.ebschool.service.LevelServiceLocal;

import javax.ejb.EJB;

/**
 * User: michau
 * Date: 5/22/13
 */
public class LevelElement implements ResponseEntityBean<Level>{

    private Long id;
    private String name;

    @Override
    public void init(Level level) {
        setId(id);
        setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
