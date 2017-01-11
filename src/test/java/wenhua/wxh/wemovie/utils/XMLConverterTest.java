package wenhua.wxh.wemovie.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wenhua.wxh.wemovie.config.RootConfig;
import wenhua.wxh.wemovie.wechat.beans.xml.Message;

import java.io.ByteArrayInputStream;

/**
 * Created by maroon on 17-1-10.
 * Package wenhua.wxh.wemovie.utils
 * DES:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class XMLConverterTest {

    private XMLConverter xmlConverter;
    private static Logger logger = LoggerFactory.getLogger(XMLConverter.class);

    String xmlString = " <xml>\n" +
            " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
            " <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
            " <CreateTime>1348831860</CreateTime>\n" +
            " <MsgType><![CDATA[image]]></MsgType>\n" +
            " <PicUrl><![CDATA[this is a url]]></PicUrl>\n" +
            " <MediaId><![CDATA[media_id]]></MediaId>\n" +
            " <MsgId>1234567890123456</MsgId>\n" +
            " </xml>";

    @Test
    public void xml2Object() throws Exception {
        Message message = (Message) xmlConverter.xml2Object(new ByteArrayInputStream(xmlString.getBytes()));
        logger.info(message.toString());
        logger.info(message.getToUserName());
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