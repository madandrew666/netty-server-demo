package com.pb.server.redisUtil;

import com.pb.server.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * Created by piecebook on 2016/8/26.
 */
public class RedisUtil {
    private Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private RedisTemplate<Serializable, Object> redisTemplate;


    public void removeForAHash(final String map_name,final List<String> keys){
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(map_name);
        boundHashOperations.delete(keys);
    }


    public void removeForAHash(final String map_name,final String key){
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(map_name);
        boundHashOperations.delete(key);
    }

    public List getValuesForAHash(final String map_name){
        List list = null;
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(map_name);
        list = boundHashOperations.values();
        return list;
    }

    public Object getForAHash(final String map_name,final String key){
        Object object = null;
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(map_name);
        //object = SerializeUtil.serialize(boundHashOperations.get(key));
        object = boundHashOperations.get(key);
        return object;
    }

    public void setForAHash(final String map_name, final String key, Object value) {
        try {
            BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(map_name);
            //boundHashOperations.put(key, SerializeUtil.serialize(value));
            boundHashOperations.put(key, value);
        } catch (Exception e) {
            logger.error("Error in set Redis ", e);
        }
    }


    public void setRedisTemplate(
            RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}