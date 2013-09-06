package com.ebschool.rest.core.model;

import com.ebschool.ejb.model.Level;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "level")
public class LevelElement {

    private Long id;
    private String name;

    public LevelElement() {}

    public LevelElement (Level level) {
        setId(level.getId());
        setName(level.getName());
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
