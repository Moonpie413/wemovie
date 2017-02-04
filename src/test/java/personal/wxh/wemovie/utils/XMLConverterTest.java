package personal.wxh.wemovie.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import personal.wxh.wemovie.config.DatabaseConfig;
import personal.wxh.wemovie.dataconvater.XMLConverter;
import personal.wxh.wemovie.dataconvater.mappers.Articles;
import personal.wxh.wemovie.dataconvater.mappers.Item;
import personal.wxh.wemovie.dataconvater.mappers.Message;
import personal.wxh.wemovie.config.RootConfig;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maroon on 17-1-10.
 * DES:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DatabaseConfig.class})
public class XMLConverterTest {

    private XMLConverter xmlConverter;
    private static Logger logger = LoggerFactory.getLogger(XMLConverter.class);

    String xmlString = " <dataconvater>\n" +
            " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
            " <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
            " <CreateTime>1348831860</CreateTime>\n" +
            " <MsgType><![CDATA[image]]></MsgType>\n" +
            " <PicUrl><![CDATA[this is a url]]></PicUrl>\n" +
            " <MediaId><![CDATA[media_id]]></MediaId>\n" +
            " <MsgId>1234567890123456</MsgId>\n" +
            " </dataconvater>";

    @Test
    public void xml2Object() throws Exception {
        Message message = new Message();
        Item item1 = new Item("haha", "des", "www.google.com", "www.alphaABC.com");
        Item item2 = new Item("hehe", "des", "www.baidu.com", "www.baidu.com");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        Articles articles = new Articles(itemList);
        message.setTitle("message title");
        message.setCreateTime(System.currentTimeMillis());
        message.setArticles(articles);
        message.setArticleCount(itemList.size());
        logger.info(xmlConverter.object2XML(message));
    }

    @Test
    public void object2XML() throws Exception {
        Message message = (Message) xmlConverter.xml2Object(new ByteArrayInputStream(xmlString.getBytes()));
        logger.info(xmlConverter.object2XML(message));
    }

    @Autowired
    public void setXmlConverter(XMLConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }
}