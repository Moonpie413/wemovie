package wenhua.wxh.wemovie.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by maroon on 17-1-9.
 * Package wenhua.wxh.wemovie.utils
 * DES: redis工具类
 */
@Component
public class RedisUtil {

    private final RedisTemplate<String, JsonNode> redisTemplate;

    @Autowired
    public RedisUtil(RedisTemplate<String, JsonNode> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public JsonNode get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public JsonNode getAndSet(String key, JsonNode value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    public void set(String key, JsonNode value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
