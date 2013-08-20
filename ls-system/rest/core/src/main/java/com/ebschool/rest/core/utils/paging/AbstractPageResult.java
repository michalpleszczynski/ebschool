package com.ebschool.rest.core.utils.paging;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * User: michau
 * Date: 5/31/13
 */
public abstract class AbstractPageResult<T> implements PageResult<T>{

    private Collection<T>  pageElements;

    public AbstractPageResult(Collection<T> pageElements){
        this.pageElements = pageElements != null ? pageElements : Collections.<T>emptyList();
    }

    @Override
    public Collection<T> getPageElements() {
        return pageElements;
    }

    @Override
    public Iterator<T> iterator(){
        return getPageElements().iterator();
    }

}
