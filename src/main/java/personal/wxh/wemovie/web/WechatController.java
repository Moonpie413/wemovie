package personal.wxh.wemovie.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import personal.wxh.wemovie.dataconvater.XMLConverter;
import personal.wxh.wemovie.dataconvater.mappers.Articles;
import personal.wxh.wemovie.dataconvater.mappers.Item;
import personal.wxh.wemovie.dataconvater.mappers.Message;
import personal.wxh.wemovie.global.DoubanURL;
import personal.wxh.wemovie.http.douban.modules.DoubanSearchResult;
import personal.wxh.wemovie.http.douban.modules.Subject;
import personal.wxh.wemovie.http.douban.request.DoubanSearchImpl;
import personal.wxh.wemovie.http.douban.request.IDoubanSearch;
import personal.wxh.wemovie.http.douban.request.KeyType;
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
    private IDoubanSearch doubanSearch;
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
    public void setDoubanSearch(IDoubanSearch doubanSearch) {
        this.doubanSearch = doubanSearch;
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
            String mesType = messageReceived.getMsgType();
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
                        System.currentTimeMillis(), Message.TEXT, "服务器开小差，请稍后再试");
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
                e.printStackTrace();
            }
        }
    }

    public String handleTextMessage(Message message) throws IOException, JAXBException, Exception {
        String content = message.getContent();
        logger.info("收到来自用户 {} 的消息： {}", message.getFromUserName(), content);
        Message messageReturn = new Message();
        messageReturn.setFromUserName(message.getToUserName());
        messageReturn.setToUserName(message.getFromUserName());
        messageReturn.setCreateTime(System.currentTimeMillis());
        messageReturn.setMsgType(Message.NEWS);
        DoubanSearchResult searchResult = doubanSearch.search(KeyType.q, content, DoubanSearchImpl.DEFAULT_COUNT,
                DoubanSearchImpl.DEFAULT_START);
        List<Item> itemList = new ArrayList<>();
        List<Subject> subjects = searchResult.getSubjects();
        int countNum = subjects.size();
        Item itemHead;
        if (countNum > 0) {
            itemHead = new Item("以下为关于 " + content + " 的结果 " + searchResult.getCount());
        } else {
            messageReturn.setMsgType(Message.TEXT);
            messageReturn.setContent("没有找到关于\n" + content + "\n" + "的结果");
            return xmlConverter.object2XML(messageReturn);
        }
        itemList.add(itemHead);
        for (int i = 0; i < countNum; i++) {
            Subject subject = subjects.get(i);
            Item item = new Item(subject.getTitle());
            item.setPicUrl(subject.getImages().get("large"));
            item.setUrl(subject.getAlt());
            itemList.add(item);
        }
        int totalNum = searchResult.getTotal();
        int more = totalNum - countNum;
        if (more > 0) {
            Item itemTail = new Item("还有剩余" + more + "条结果， 点击去豆瓣查看");
            itemTail.setUrl(DoubanURL.SUBJECT_SEARCH_URL + content);
            itemList.add(itemTail);
        }
        Articles articles = new Articles(itemList);
        messageReturn.setArticleCount(itemList.size());
        messageReturn.setArticles(articles);
        return xmlConverter.object2XML(messageReturn);
    }

}
