package wenhua.wxh.wemovie.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wenhua.wxh.wemovie.utils.PropertyUtil;
import wenhua.wxh.wemovie.utils.XMLConverter;
import wenhua.wxh.wemovie.wechat.beans.xml.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangxh on 17-1-7.
 * Package wenhua.wxh.wemovie.web
 * DES: 微信验证控制器类
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    private PropertyUtil propertyUtil;
    private XMLConverter xmlConverter;
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private void setPropertyUtil(PropertyUtil propertyUtil) {
        this.propertyUtil = propertyUtil;
    }

    @GetMapping(consumes = "application/json")
    public String auth(@RequestParam String signature, @RequestParam String timestamp,
                       @RequestParam String nonce, @RequestParam String echostr) {
        String token = propertyUtil.getProperty(PropertyUtil.TOKEN);
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
    public void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, JAXBException {
        Message messageReceived = (Message) xmlConverter.xml2Object(request.getInputStream());
        String mesType = messageReceived.getMsgType();
        switch (mesType) {
            case Message.TEXT:
                handleTextMessage(messageReceived);
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

        response.setStatus(200);
        response.getWriter().write("succeed");
    }

    public void handleTextMessage(Message message) {

    }

    @Autowired
    public void setXmlConverter(XMLConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }
}
