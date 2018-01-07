package com.lami.foodie.transaction;

import lombok.Data;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by xjk on 10/4/17.
 */
@Data
public class AddresServiceImpl implements AddressService {

    private DataSource dataSource;

    private AddressDao addressDao;

    private TransactionDefinition transactionDefinition;
    private PlatformTransactionManager txManager;

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void save(final Address address) throws Exception {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);

        TransactionStatus transactionStatus = txManager.getTransaction(transactionDefinition);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                addressDao.save(address);
            }
        });
    }

    public int countAll() {
        return addressDao.countAll();
    }


    public void testPlatformTransactionManager() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try{
            connection.prepareStatement("select * from student;").execute();
            PreparedStatement pstmt = connection.prepareStatement("select * from student;");
            pstmt.execute();
            txManager.commit(status);
        }catch(Exception ex){
            status.setRollbackOnly();
            txManager.rollback(status);
        }finally{
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
}
