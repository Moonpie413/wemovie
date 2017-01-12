package personal.wxh.wemovie.utils;

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
import personal.wxh.wemovie.config.RootConfig;
import personal.wxh.wemovie.http.HttpTool;
import personal.wxh.wemovie.props.PropertyGetter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maroon on 17-1-9.
 * DES:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class HttpToolTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PropertyGetter propertyGetter;
    private HttpTool httpTool;

    @Autowired
    public void setPropertyGetter(PropertyGetter propertyGetter) {
        this.propertyGetter = propertyGetter;
    }

    @Autowired
    public void setHttpTool(HttpTool httpTool) {
        this.httpTool = httpTool;
    }

    @Test
    public void testDoGet() throws Exception {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "client_credential"));
        params.add(new BasicNameValuePair("APPID", propertyGetter.getProperty(PropertyGetter.APPID)));
        params.add(new BasicNameValuePair("secret", propertyGetter.getProperty(PropertyGetter.APPSECRET)));
        HttpResponse response = httpTool.doGet("https://api.weixin.qq.com/cgi-bin/token", params, null);
        logger.info(response.toString());
    }

    @Test
    public void testDoGetWithJSON() throws Exception {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "client_credential"));
        params.add(new BasicNameValuePair("APPID", propertyGetter.getProperty(PropertyGetter.APPID)));
        params.add(new BasicNameValuePair("secret", propertyGetter.getProperty(PropertyGetter.APPSECRET)));
        JsonNode node = httpTool.doGetWithJSON("https://api.weixin.qq.com/cgi-bin/token", params, null);
        logger.info(String.valueOf(node.get("access_token")));
    }

    @Test
    public void testDoPost() throws Exception {
        String xmlString = " <xmlmapper>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName><![CDATA[fromUser]]></FromUserName> \n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[text]]></MsgType>\n" +
                " <String><![CDATA[this is a test]]></String>\n" +
                " <MsgId>1234567890123456</MsgId>\n" +
                " </xmlmapper>";

    }


}