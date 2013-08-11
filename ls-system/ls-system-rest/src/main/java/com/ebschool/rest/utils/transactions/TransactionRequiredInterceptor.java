package com.ebschool.rest.utils.transactions;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.Status;
import java.io.Serializable;

/**
 * User: michau
 * Date: 6/1/13
 */
// add transactions to a jersey resource (plain cdi bean)
@TransactionRequired
@Interceptor
public class TransactionRequiredInterceptor implements Serializable {

    @Resource
    private javax.transaction.UserTransaction utx;

    @AroundInvoke
    public Object openIfNoTransaction(InvocationContext ic) throws Throwable {
        boolean startedTransaction = false;
        if (utx.getStatus() != Status.STATUS_ACTIVE) {
            utx.begin();
            startedTransaction = true;
        }

        Object ret = null;
        try {
            ret = ic.proceed();

            if (startedTransaction) {
                utx.commit();
            }
        } catch (Throwable t) {
            if (startedTransaction) {
                utx.rollback();
            }

            throw t;
        }

        return ret;
    }
}
