package personal.wxh.wemovie.http.wechat.token;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import personal.wxh.wemovie.global.WechatURL;
import personal.wxh.wemovie.http.HttpTool;
import personal.wxh.wemovie.props.PropertyGetter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maroon on 17-1-12.
 * DES: 调用微信的accessToken API 刷新AccessToken
 */
@Component
public class AccessTokenRequester {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenRequester.class);
    private static final int EXPIRES_IN_DEFAULT = 7200;
    private HttpTool httpTool;
    private PropertyGetter propertyGetter;

    @Autowired
    public void setHttpTool(HttpTool httpTool) {
        this.httpTool = httpTool;
    }

    @Autowired
    public void setPropertyGetter(PropertyGetter propertyGetter) {
        this.propertyGetter = propertyGetter;
    }

    public String requestAccessToken() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "client_credential"));
        params.add(new BasicNameValuePair("APPID", propertyGetter.getProperty(PropertyGetter.APPID)));
        params.add(new BasicNameValuePair("secret", propertyGetter.getProperty(PropertyGetter.APPSECRET)));
        JsonNode node = null;
        logger.info("获取Access Token...");
        try {
            node = httpTool.doGetWithJSON(WechatURL.ACCESS_TOKEN_URL, params, null);
            if (node != null) {
                if (node.has("access_token")) {
                    int expires_in = node.get("expires_in").asInt();
                    if (expires_in != EXPIRES_IN_DEFAULT) logger.warn("access_token刷新时间变更为 {}，请手动修改", expires_in);
                    return node.get("access_token").asText();
                } else if (node.has("errcode")) {
                    logger.error("access token请求错误， errorCode: {}", node.get("errcode"));
                }
            }
        } catch (IOException e) {
            logger.error("accessToken请求异常", e);
        }
        return null;
    }
}
