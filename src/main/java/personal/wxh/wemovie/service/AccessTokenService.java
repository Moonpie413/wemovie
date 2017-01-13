package personal.wxh.wemovie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.wxh.wemovie.dao.redis.RedisOperator;
import personal.wxh.wemovie.http.wechat.token.HttpAccessToken;

import javax.annotation.Resource;

/**
 * Created by maroon on 17-1-12.
 * DES:
 */
@Service
public class AccessTokenService {

    @Resource
    private RedisOperator redisOperator;
    public static final Logger logger = LoggerFactory.getLogger(AccessTokenService.class);

    private HttpAccessToken httpAccessToken;

    public void refreshAccessToken() {
        String accessToken = this.httpAccessToken.requestAccessToken();
        if (accessToken != null) {
            String accessTokenOld = (String) redisOperator.getAndSet("access_token", accessToken);
            logger.info("刷新 access_token 缓存 {} -> {}", accessTokenOld, accessToken);
        } else {
            logger.warn("access token 缓存刷新失败...");
        }
    }

    public String getAccessToken() {
        return (String) redisOperator.get("access_token");
    }

    @Autowired
    public void setHttpAccessToken(HttpAccessToken httpAccessToken) {
        this.httpAccessToken = httpAccessToken;
    }

}
