package personal.wxh.wemovie.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import personal.wxh.wemovie.dataconvater.XMLConverter;
import personal.wxh.wemovie.dataconvater.mappers.Message;

import personal.wxh.wemovie.props.PropertyGetter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.Writer;
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
    private IMessageHandler messageHandler;
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private void setPropertyGetter(PropertyGetter propertyGetter) {
        this.propertyGetter = propertyGetter;
    }

    @Autowired
    public void setXmlConverter(XMLConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }

    @Autowired
    public void setMessageHandler(IMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
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
        String result = null;
        Message messageReceived = null;
        Writer writer = null;
        try {
            writer = response.getWriter();
            // 先返回success再进行逻辑
            response.setStatus(200);
            writer.write("success");
            messageReceived = (Message) xmlConverter.xml2Object(request.getInputStream());
            logger.info("收到来自用户 {} 的 {} 消息",
                    messageReceived.getFromUserName(), messageReceived.getMsgType());
            String mesType = messageReceived.getMsgType();
            switch (mesType) {
                case Message.TEXT:
                    result = handleTextMessage(messageReceived);
                case Message.IMAGE:
                    break;
                case Message.VOICE:
                    result = handleVoiceMessage(messageReceived);
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
                writer.write(result);
                logger.info("用户 {} 的消息处理完毕", messageReceived.getFromUserName());
            }
        } catch (IOException e) {
            logger.error("消息IO错误", e);
        } catch (JAXBException e) {
            logger.error("DOM转换失败", e);
        } catch (Exception e) {
            logger.error("出现异常", e);
        } finally {
            if (result == null) {
                assert messageReceived != null;
                Message err = new Message(messageReceived.getFromUserName(), messageReceived.getToUserName(),
                        System.currentTimeMillis(), Message.TEXT);
                err.setContent("服务器出错，请联系开发者或者留言");
                try {
                    writer.write(xmlConverter.object2XML(err));
                } catch (IOException e) {
                    logger.error("消息IO错误", e);
                } catch (JAXBException e) {
                    logger.error("DOM转换失败", e);
                }
            }
            try {
                writer.close();
            } catch (IOException e) {
                logger.error("writer流关闭出错", e);
            }
        }
    }

    private String handleTextMessage(Message message) throws IOException, JAXBException {
        return messageHandler.handleTextMessage(message);
    }

    private String handleVoiceMessage(Message message) throws IOException, JAXBException {
        return messageHandler.handleVoiceMessage(message);
    }

}
