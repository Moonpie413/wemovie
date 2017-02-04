package personal.wxh.wemovie.dao.redis;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by maroon on 17-1-9.
 * DES: redis工具类
 */
@Repository
public class RedisOperator {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisOperator(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public String getAndSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
