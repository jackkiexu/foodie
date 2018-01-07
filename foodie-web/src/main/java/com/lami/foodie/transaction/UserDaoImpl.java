package com.lami.foodie.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xjk on 10/4/17.
 */
public class UserDaoImpl extends NamedParameterJdbcDaoSupport implements UserDao {

    public static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private final String INSERT_SQL = "insert into user (name) values (:name)";
    private final String COUNT_ALL_SQL = "select count(*) from user";

    public void save(User user) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("name", user.getName());
        getNamedParameterJdbcTemplate().update(INSERT_SQL, param);

    }

    public int countAll() {

        return getJdbcTemplate().queryForInt(COUNT_ALL_SQL);
    }
}
