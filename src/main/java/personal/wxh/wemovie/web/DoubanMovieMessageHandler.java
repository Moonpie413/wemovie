package personal.wxh.wemovie.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: wangxh
 * 创建日期: 17-2-5
 * 简介: 使用豆瓣电影作为数据源的消息处理器
 */
@Component
public class DoubanMovieMessageHandler implements IMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(DoubanMovieMessageHandler.class);

    private IDoubanSearch doubanSearch;
    private XMLConverter xmlConverter;

    @Autowired
    public void setDoubanSearch(IDoubanSearch doubanSearch) {
        this.doubanSearch = doubanSearch;
    }

    @Autowired
    public void setXmlConverter(XMLConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }

    @Override
    public String handleTextMessage(Message message) throws IOException, JAXBException {
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
            itemHead = new Item("以下为关于 " + content + " 的结果 ");
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

    @Override
    public String handleImageMessage(Message message) {
        return null;
    }

    @Override
    public String handleVoiceMessage(Message message) {
        return null;
    }

    @Override
    public String handleVideoMessage(Message message) {
        return null;
    }

    @Override
    public String handleShortVideoMessage(Message message) {
        return null;
    }

    @Override
    public String handleLocationMessage(Message message) {
        return null;
    }
}
