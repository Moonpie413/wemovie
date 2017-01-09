package wenhua.wxh.wemovie.wechat.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wenhua.wxh.wemovie.utils.HttpUtil;
import wenhua.wxh.wemovie.utils.PropertyUtil;
import wenhua.wxh.wemovie.utils.RedisUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maroon on 17-1-9.
 * Package wenhua.wxh.wemovie.wechat
 * DES:
 */
@Component
public class AccessTokenTask {
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenTask.class);

    private HttpUtil httpUtil;
    private RedisUtil redisUtil;
    private PropertyUtil propertyUtil;

    /**
     * 每隔 fixedRate / 1000 s 刷新accessToken
     * 微信要求为7200刷新一次
     */
    @Scheduled(fixedRate = 7000 * 1000)
    public void refreshAccessToken() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "client_credential"));
        params.add(new BasicNameValuePair("APPID", propertyUtil.getProperty(PropertyUtil.APPID)));
        params.add(new BasicNameValuePair("secret", propertyUtil.getProperty(PropertyUtil.APPSECRET)));
        JsonNode node = null;
        try {
            logger.info("获取Access Token...");
            node = httpUtil.doGetWithJSON("https://api.weixin.qq.com/cgi-bin/token", params, null);
        } catch (IOException e) {
            logger.error("AccessToken 获取失败");
            e.printStackTrace();
        }
        if (node != null && node.has("access_token")) {
            logger.info("刷新AccessToken... {} -> {}",
                    redisUtil.getAndSet("accessToken", node).get("access_token"),
                    node.get("access_token"));
        }
    }

    @Autowired
    public void setHttpUtil(HttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Autowired
    public void setPropertyUtil(PropertyUtil propertyUtil) {
        this.propertyUtil = propertyUtil;
    }

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
