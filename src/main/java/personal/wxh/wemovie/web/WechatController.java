package personal.wxh.wemovie.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import personal.wxh.wemovie.xml.XMLConverter;
import personal.wxh.wemovie.xml.mappers.Articles;
import personal.wxh.wemovie.xml.mappers.Item;
import personal.wxh.wemovie.xml.mappers.Message;
import personal.wxh.wemovie.props.PropertyGetter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

/**
 * Created by wangxh on 17-1-7.
 * Package wenhua.wxh.wemovie.web
 * DES: 微信验证控制器类
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    private PropertyGetter propertyGetter;
    private XMLConverter xmlConverter;
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private void setPropertyGetter(PropertyGetter propertyGetter) {
        this.propertyGetter = propertyGetter;
    }

    @GetMapping
    public String auth(@RequestParam String signature, @RequestParam String timestamp,
                       @RequestParam String nonce, @RequestParam String echostr) {
        String token = propertyGetter.getProperty(PropertyGetter.TOKEN);
        List<String> list = Arrays.asList(token, nonce, timestamp);
        Collections.sort(list);
        String mySignature = DigestUtils.sha1Hex(list.get(0) + list.get(1) + list.get(2));
        if (mySignature.equals(signature)) {
            logger.info("wechat auth succeed");
            return echostr;
        }
        logger.info("wechat auth failed");
        return "bad request";
    }

    @PostMapping
    public void acceptMessage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Message messageReceived = (Message) xmlConverter.xml2Object(request.getInputStream());
            String mesType = messageReceived.getMsgType();
            String result = null;
            switch (mesType) {
                case Message.TEXT:
                    result = handleTextMessage(messageReceived);
                case Message.IMAGE:
                    break;
                case Message.VOICE:
                    break;
                case Message.VIDEO:
                    break;
                case Message.SHORTVIDEO:
                    break;
                case Message.LOCATION:
                    break;
                default:
                    throw new RuntimeException("消息类型不合法");
            }
            if (result != null) {
                response.setStatus(200);
                response.getWriter().write(result);
            } else {
                response.setStatus(500);
            }
        } catch (IOException e) {
            logger.error("消息IO错误", e);
        } catch (JAXBException e) {
            logger.error("DOM转换失败", e);
        }

    }

    public String handleTextMessage(Message message) throws IOException, JAXBException {
        logger.info("收到来自 {} 的消息： {}", message.getFromUserName(), message.getContent());
        Message messageReturn = new Message();
        messageReturn.setFromUserName(message.getToUserName());
        messageReturn.setToUserName(message.getFromUserName());
        messageReturn.setCreateTime(System.currentTimeMillis());
        messageReturn.setMsgType(Message.NEWS);
        Item item1 = new Item("你好", "你好", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/ThreeCirclesPlain.svg/200px-ThreeCirclesPlain.svg.png", "www.alphaABC.com");
        Item item2 = new Item("hello", "hello wechat", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/ThreeCirclesPlain.svg/200px-ThreeCirclesPlain.svg.png", "www.baidu.com");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        Articles articles = new Articles(itemList);
        messageReturn.setArticleCount(itemList.size());
        messageReturn.setArticles(articles);
        return xmlConverter.object2XML(messageReturn);
    }

    @Autowired
    public void setXmlConverter(XMLConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }

}
