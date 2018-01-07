package com.lami.foodie.transaction;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by xjk on 10/4/17.
 */
@Data
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserDao userDao;
    public AddressService addressService;
    public PlatformTransactionManager txManager;


    public void save(final User user) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                userDao.save(user);
                user.getAddress().setUserId(user.getId());
                try{
                    addressService.save(user.getAddress());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public int countAll() {
        return 0;
    }
}
