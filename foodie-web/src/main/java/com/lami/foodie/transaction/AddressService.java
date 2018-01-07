package com.lami.foodie.transaction;

/**
 * Created by xjk on 10/4/17.
 */
public interface AddressService {

    public void save(Address address) throws Exception;

    public int countAll();

}
