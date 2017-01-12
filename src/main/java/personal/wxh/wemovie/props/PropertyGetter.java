package personal.wxh.wemovie.props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by maroon on 17-1-8.
 * DES: 属性配置映射，对应classpath下props文件夹下的配置
 */
@Component
@PropertySource(value = { "classpath:props/wechat.properties", "classpath:props/redis.properties" })
public class PropertyGetter {
    // 安全类配置
    public static final String APPID = "wechat.securty.appID";
    public static final String APPSECRET = "wechat.security.appSecret";
    public static final String TOKEN = "wechat.security.token";
    public static final String ENCODINGAESKey = "wechat.security.encodingAESKey";
    // 安全类配置结束

    // Redis相关
    public static final String REDIS_HOSTNAME = "redis.hostname";
    public static final String REDIS_PORT = "redis.port";
    public static final String REDIS_PASS = "redis.pass";
    // Redis相关结束

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 根据key获取配置的值
     * @param key 属性key
     * @return value 属性value
     */
    public String getProperty(String key) {
        return environment.getProperty(key);
    }

    /**
     * 根据key获取配置的值
     * @param key 属性key
     * @param defaultValue 属性默认value
     * @return value 属性value
     */
    public String getProperty(String key, String defaultValue) {
        return environment.getProperty(key, defaultValue);
    }

    /**
     * 根据key获取配置的值的int类型
     * @param key 属性key
     * @param defaultValue 属性默认value
     * @return value 属性value
     */
    public Integer getPropertyInt(String key, String defaultValue) {
        return Integer.parseInt(environment.getProperty(key, defaultValue));
    }
}
