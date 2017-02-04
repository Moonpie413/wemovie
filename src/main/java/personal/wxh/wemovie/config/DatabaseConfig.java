package personal.wxh.wemovie.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.resource.ResourceResolver;
import personal.wxh.wemovie.props.PropertyGetter;

import javax.sql.DataSource;

/**
 * Created by maroon on 17-1-13.
 * DES:
 */
@Configuration
@ComponentScan("personal.wxh.wemovie.dao")
@MapperScan("personal.wxh.wemovie.dao.mappers")
@EnableCaching
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    private PropertyGetter propertyGetter;

    @Bean
    public DataSource dataSource() {
        return new PooledDataSource(
                propertyGetter.getProperty(PropertyGetter.JDBC_DRIVER),
                propertyGetter.getProperty(PropertyGetter.JDBC_URL),
                propertyGetter.getProperty(PropertyGetter.JDBC_USER),
                propertyGetter.getProperty(PropertyGetter.JDBC_PASS));
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(propertyGetter.getProperty(PropertyGetter.REDIS_HOSTNAME, "127.0.0.1"));
        factory.setPort(propertyGetter.getPropertyInt(PropertyGetter.REDIS_PORT, "6379"));
        // TODO 暂时不验证密码
//        factory.setPassword(propertyGetter.getProperty(PropertyGetter.REDIS_PASS, ""));
        return factory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return redisTemplate;
    }


    @Autowired
    public void setPropertyGetter(PropertyGetter propertyGetter) {
        this.propertyGetter = propertyGetter;
    }
}
