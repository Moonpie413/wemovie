package wenhua.wxh.wemovie.config;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import wenhua.wxh.wemovie.utils.PropertyUtil;
import wenhua.wxh.wemovie.utils.XMLConverter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxh on 17-1-7.
 * Package wenhua.wxh.wemovie.config
 * DES:
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "wenhua.wxh.wemovie",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class))
public class RootConfig {

    private PropertyUtil propertyUtil;

    @Autowired
    public void setPropertyUtil(PropertyUtil propertyUtil) {
        this.propertyUtil = propertyUtil;
    }

    @Bean(destroyMethod = "close")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        return connectionManager;
    }

    @Bean(destroyMethod = "close")
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager()).build();
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(wenhua.wxh.wemovie.wechat.beans.xml.Message.class);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("jaxb.formatted.output", true);
        jaxb2Marshaller.setMarshallerProperties(map);
        return jaxb2Marshaller;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public RedisTemplate<String, JsonNode> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, JsonNode> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<JsonNode>(JsonNode.class));
        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(propertyUtil.getProperty(PropertyUtil.REDIS_HOSTNAME, "127.0.0.1"));
        factory.setPort(propertyUtil.getPropertyInt(PropertyUtil.REDIS_PORT, "6379"));
        factory.setPassword(propertyUtil.getProperty(PropertyUtil.REDIS_PASS, ""));
        return factory;
    }

}
