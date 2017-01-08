/**
 * store message in redis
 *
 * 1. message store
 *    hset topic timestamp message
 *
 * 2. hset topic brokerId Set<UUID>
 *
 * 3. 每个用户读取信息的偏移量
 *    hset uuid topic offset
 *
 * Created by xjk on 11/10/16.
 */
package com.lami.tarsier.store;