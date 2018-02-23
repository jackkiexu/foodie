package com.lami.foodie.transaction;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xjk on 2/14/18.
 */
public class PlatformTransactionManagerTest {

    private static final String CREATE_TABLE_SQL = "create table test" +
            "(id int(10) AUTO_INCREMENT PRIMARY KEY, " +
            "name varchar(100))";
    private static final String DROP_TABLE_SQL = "drop table test";
    private static final String INSERT_SQL = "insert into test(name) values(?)";
    private static final String COUNT_SQL = "select count(*) from test";

    public static void main(String[] args) {
        // 定义连接池
        DataSource dataSource = getDataSource();
        // 先建 PlatformTransactionManager
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        // 定义 TransactionDefinition
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        // 获取 TransactionStatus
        TransactionStatus transactionStatus = transactionManager.getTransaction(definition);
        // 从连接池中获取 <- 这里其实是将 DataSource <--> Connection 放到 ThreadLocal 里面
        Connection con = DataSourceUtils.getConnection(dataSource);
        try {
            // 执行 SQL
            con.prepareStatement(CREATE_TABLE_SQL).execute();
            PreparedStatement pstmt = con.prepareStatement(INSERT_SQL);
            pstmt.setString(1, "test");
            pstmt.execute();
            ResultSet resultSet = con.prepareStatement(COUNT_SQL).executeQuery();
            // 打印查询结果
            while(resultSet.next()){ System.out.println(resultSet.getString(1));}
            con.prepareStatement(DROP_TABLE_SQL).execute();
            transactionManager.commit(transactionStatus);
        } catch (Exception e) { // 遇到异常就直接回滚
            transactionManager.rollback(transactionStatus);
        }
    }

    // 设置数据库连接池
    public static DataSource getDataSource(){
        org.apache.commons.dbcp.BasicDataSource basicDataSource = new org.apache.commons.dbcp.BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/tuomatuo?useUnicode=true&characterEncoding=UTF8");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("123456");
        return basicDataSource;
    }
}
