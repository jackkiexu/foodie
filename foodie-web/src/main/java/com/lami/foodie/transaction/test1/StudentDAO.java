package com.lami.foodie.transaction.test1;

/**
 * Created by xjk on 10/5/17.
 */

import java.util.List;

import javax.sql.DataSource;

import org.springframework.transaction.PlatformTransactionManager;

public interface StudentDAO {

    public void setDataSource(DataSource ds);

    public void setTransactionManager(PlatformTransactionManager transactionManager);

    public void create(String name, Integer age, Integer marks, Integer year);

    public StudentMarks getStudent(Integer id);

    public List<StudentMarks> listStudents();

    public void delete(Integer id);

    public void update(Integer id, String name, Integer age, Integer marks, Integer year);
}