package com.lami.foodie.transaction;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

/**
 * Created by xjk on 10/4/17.
 */
public class SimpleServiceImpl implements SimpleService {

    // single TransactionTemplate shared amongst all method in instance
    private final TransactionTemplate transactionTemplate;

    // use constructor-injection to supply the PlatformTransactionManager
    public SimpleServiceImpl(TransactionTemplate transactionTemplate) {
        Assert.notNull(transactionTemplate, "The 'transactionManager' argument must not be null");
        this.transactionTemplate = transactionTemplate;
    }

    public Object someServiceMethod() {

        return transactionTemplate.execute(new TransactionCallback<Object>() {
            // the code in this method executes in a transactional context
            public Object doInTransaction(TransactionStatus status) {
                // updateOperation1();
                return null;
            }
        });
    }

}
