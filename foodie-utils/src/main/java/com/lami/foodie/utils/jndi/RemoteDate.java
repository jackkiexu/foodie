package com.lami.foodie.utils.jndi;

import java.rmi.Remote;
import java.util.Date;

/**
 * Created by xjk on 7/1/17.
 */
// 在RMI中绑JNDI的限制是，绑定的对象必须是Remote类型
// 外部扩展,可以内部扩展也可以外部扩展
class RemoteDate extends Date implements Remote {
};
