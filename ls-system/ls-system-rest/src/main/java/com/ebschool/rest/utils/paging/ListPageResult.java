package com.ebschool.rest.utils.paging;

import java.util.List;

/**
 * User: michau
 * Date: 5/31/13
 */
public class ListPageResult<T> extends AbstractPageResult<T> {

    public ListPageResult(List<T> pageElements) {
        super(pageElements);
    }

}
