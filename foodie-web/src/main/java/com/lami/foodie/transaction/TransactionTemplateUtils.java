package com.lami.foodie.transaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by xjk on 10/4/17.
 */
public class TransactionTemplateUtils {

    private static TransactionTemplate getTransactionTemplate(
            PlatformTransactionManager txManager, int propagationBehavior,
            int isolationLevel
    ){
        TransactionTemplate transactionTemplate = new TransactionTemplate(txManager);
        transactionTemplate.setPropagationBehavior(propagationBehavior);
        transactionTemplate.setIsolationLevel(isolationLevel);
        return transactionTemplate;
    }

    public static TransactionTemplate getDefaultTransactionTemplate(PlatformTransactionManager txManager){
        return getTransactionTemplate(txManager, TransactionDefinition.PROPAGATION_REQUIRED,
                TransactionDefinition.ISOLATION_DEFAULT);
    }

}
