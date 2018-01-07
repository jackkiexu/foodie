package com.lami.foodie.transaction;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xjk on 10/4/17.
 */
public class AddressDaoImp extends NamedParameterJdbcDaoSupport implements AddressDao{
    private final String INSERT_SQL = "insert into address(province, city, street, user_id)" + "values(:province, :city, :street, :userId)";
    private final String COUNT_ALL_SQL = "select count(*) from address";

    public void save(Address address){
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
      /*SqlParameterSource source = new BeanPropertySqlParameterSource(address);
      getNamedParameterJdbcTemplate().update(INSERT_SQL, source, generatedKeyHolder);*/
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("province", address.getProvince());
        para.put("city", address.getCity());
        para.put("street", address.getStreet());
        para.put("userId", address.getUserId());
        getNamedParameterJdbcTemplate().update(INSERT_SQL, para);
    }

    public int countAll() {
        return getJdbcTemplate().queryForInt(COUNT_ALL_SQL);
    }
}
