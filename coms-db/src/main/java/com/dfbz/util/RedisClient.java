package com.dfbz.util;

import com.dfbz.domain.ComsCartItem;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    /*
     * @ClassName RedisClient
     * @Desc TODO   设置缓存（没有时间限制）
     */
    public void set(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }

    /*
     * @ClassName RedisClient
     * @Desc TODO   设置缓存（有时间限制，单位为 秒）
     */
    public void set(String key, Object value,long timeout){
        redisTemplate.opsForValue().set(key, value,timeout, TimeUnit.SECONDS);
    }

    /*
     * @ClassName RedisClient
     * @Desc TODO   删除缓存，并返回是否删除成功
     */
    public boolean clear(String key){
        redisTemplate.delete(key);
        // 如果还存在这个 key 就证明删除失败
        if(redisTemplate.hasKey(key)){
            return false;
            // 不存在就证明删除成功
        }else{
            return true;
        }
    }

    /*
     * @ClassName RedisClient
     * @Desc TODO   取出缓存
     */
    public Object get(String key){
        if(redisTemplate.hasKey(key)){
            return redisTemplate.opsForValue().get(key);
        }else{
            return null;
        }
    }

    /*
     * @ClassName RedisClient
     * @Desc TODO   获取失效时间（-2：失效 / -1：没有时间限制）
     */
    public long getExpire(String key){
        // 判断是否存在
        if(redisTemplate.hasKey(key)){
            return redisTemplate.getExpire(key);
        }else{
            return Long.parseLong(-2+"");
        }
    }

    /**
     * 添加hash格式操作
     * @param key
     * @param hashKey
     * @param value
     */
    public void put(String key,Object hashKey,Object value){
        redisTemplate.opsForHash().put(key,hashKey,value);
    }

    /**
     * 通过指定key获取list集合
     * @param key
     * @return
     */
    public List<Object> values(String key){
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 通过指定key获取map集合
     * @param key
     * @return
     */
    public Map<Object,Object> entries(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 根据key和hashKey获取值
     * @param key
     * @param hashKey
     * @return
     */
    public Object get(String key,Object hashKey){
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取key的值的长度
     * @param key
     * @return
     */
    public long size(String key){
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 根据key和多个hashKeys删除多个值
     * @param key
     * @param hashKeys
     * @return
     */
    public long delete(String key,Object...hashKeys){
        return redisTemplate.opsForHash().delete(key,hashKeys);
    }


}
