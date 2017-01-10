package wenhua.wxh.wemovie.wechat.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
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
@Service
public class AccessTokenService {
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenService.class);
    private static final int EXPIRES_IN_DEFAULT = 7200;
    private HttpUtil httpUtil;
    private RedisUtil redisUtil;
    private PropertyUtil propertyUtil;

    /**
     * 每隔 fixedRate / 1000 s 刷新accessToken
     * 微信要求为7200刷新一次
     */
    @Scheduled(fixedRate = (EXPIRES_IN_DEFAULT - 200) * 1000)
    public void refreshAccessToken() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "client_credential"));
        params.add(new BasicNameValuePair("APPID", propertyUtil.getProperty(PropertyUtil.APPID)));
        params.add(new BasicNameValuePair("secret", propertyUtil.getProperty(PropertyUtil.APPSECRET)));
        JsonNode node = null;
        logger.info("获取Access Token...");
        try {
            node = httpUtil.doGetWithJSON("https://api.weixin.qq.com/cgi-bin/token", params, null);
            if (node != null) {
                if (node.has("access_token")) {
                    int expires_in = node.get("expires_in").asInt();
                    if (expires_in != EXPIRES_IN_DEFAULT) logger.warn("access_token刷新时间变更为 {}，请手动修改", expires_in);
                    logger.info("刷新AccessToken... {} -> {}",
                            redisUtil.getAndSet("accessToken", node).get("access_token"),
                            node.get("access_token"));
                } else if (node.has("errcode")) {
                    logger.error("access token刷新失败， errorCode: {}", node.get("errcode"));
                }
            }
        } catch (IOException e) {
            logger.error("accessToken刷新IO错误");
            e.printStackTrace();
        }
    }

    public JsonNode getAccessToken() {
        return null;
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
