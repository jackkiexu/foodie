package com.lami.foodie.transaction;

/**
 * Created by xjk on 10/4/17.
 */
public interface AddressDao {
    public void save(Address address);
    public int countAll();
}
