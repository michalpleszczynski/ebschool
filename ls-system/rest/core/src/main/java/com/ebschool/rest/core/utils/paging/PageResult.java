package com.ebschool.rest.core.utils.paging;

import java.util.Collection;

/**
 * User: michau
 * Date: 5/31/13
 * In the future this will be really paged result,
 * for now just a wrapper for collections
 */
public interface PageResult<T> extends Iterable<T> {

    Collection<T> getPageElements();

//    int getPageSize();
//
//    int getPageNumber();
//
//    int getNumberOfElements();
//
//    int getTotalNumberOfElements();
//
//    boolean isFirstPage();
//
//    boolean isLastPage();
//
//    boolean hasNextPage();
//
//    boolean hasPreviousPage();
//
//    int getPreviousPageNumber();
//
//    int getNextPageNumber();
//
//    int getLastPageNumber();

}
