package wenhua.wxh.wemovie.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wenhua.wxh.wemovie.config.RootConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maroon on 17-1-9.
 * Package wenhua.wxh.wemovie.utils
 * DES:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class HttpUtilTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PropertyUtil propertyUtil;
    private HttpUtil httpUtil;

    @Autowired
    public void setPropertyUtil(PropertyUtil propertyUtil) {
        this.propertyUtil = propertyUtil;
    }

    @Autowired
    public void setHttpUtil(HttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Test
    public void testDoGet() throws Exception {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "client_credential"));
        params.add(new BasicNameValuePair("APPID", propertyUtil.getProperty(PropertyUtil.APPID)));
        params.add(new BasicNameValuePair("secret", propertyUtil.getProperty(PropertyUtil.APPSECRET)));
        HttpResponse response = httpUtil.doGet("https://api.weixin.qq.com/cgi-bin/token", params, null);
        logger.info(response.toString());
    }

    @Test
    public void testDoGetWithJSON() throws Exception {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "client_credential"));
        params.add(new BasicNameValuePair("APPID", propertyUtil.getProperty(PropertyUtil.APPID)));
        params.add(new BasicNameValuePair("secret", propertyUtil.getProperty(PropertyUtil.APPSECRET)));
        JsonNode node = httpUtil.doGetWithJSON("https://api.weixin.qq.com/cgi-bin/token", params, null);
        logger.info(String.valueOf(node.get("access_token")));
    }

    @Test
    public void testDoPost() throws Exception {
        String xmlString = " <xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName><![CDATA[fromUser]]></FromUserName> \n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[text]]></MsgType>\n" +
                " <String><![CDATA[this is a test]]></String>\n" +
                " <MsgId>1234567890123456</MsgId>\n" +
                " </xml>";

    }


}