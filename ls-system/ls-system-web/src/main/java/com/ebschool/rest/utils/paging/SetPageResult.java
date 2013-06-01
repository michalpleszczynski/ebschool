package com.ebschool.rest.utils.paging;

import java.util.Set;

/**
 * User: michau
 * Date: 5/31/13
 */
public class SetPageResult<T> extends AbstractPageResult<T> {

    public SetPageResult(Set<T> pageElements) {
        super(pageElements);
    }

}
