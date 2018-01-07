package com.lami.foodie.transaction;

import lombok.Data;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created by xjk on 10/5/17.
 */
@Data
public class TransactionTemplateTest {

    private DataSource dataSource;
    private TransactionDefinition transactionDefinition;
    private PlatformTransactionManager txManager;


    public void testPlatformTransactionManager() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try{
            connection.prepareStatement("select * from student;").execute();
            PreparedStatement pstmt = connection.prepareStatement("select * from student;");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()){
                Object id = resultSet.getObject("id");
                Object age = resultSet.getObject("age");
                Object name = resultSet.getObject("name");
                System.out.println("id:" + id + ", age:" + age + ", name:" + name);
            }
            System.out.println("****************");

            pstmt = connection.prepareStatement("INSERT INTO student (age, name) VALUES (" + "2" + ", \'" + new Date().getTime() + "\')");
            boolean result = pstmt.execute();
            System.out.println(" result : " + result);
            if( "1".equals("1"))throw new RuntimeException();
            txManager.commit(status);
        }catch(Exception ex){
            ex.printStackTrace();
            status.setRollbackOnly();
            txManager.rollback(status);
        }finally{
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

}
