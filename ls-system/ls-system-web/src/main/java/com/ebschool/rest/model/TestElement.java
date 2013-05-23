package com.ebschool.rest.model;

import com.ebschool.model.Test;
import com.ebschool.rest.ResponseEntityBean;

import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
public class TestElement implements ResponseEntityBean<Test>{

    //TODO: find some more generic approach
    public static Set<TestElement> buildSet(Set<Test> tests){
        Set<TestElement> returnSet = new HashSet<>();
        for (Test test : tests){
            TestElement testElement = new TestElement();
            testElement.init(test);
            returnSet.add(testElement);
        }
        return returnSet;
    }                    
    
    @Override
    public void init(Test test) {
        
    }
    
}
