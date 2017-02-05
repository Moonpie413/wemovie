package personal.wxh.wemovie.web;

import personal.wxh.wemovie.dataconvater.mappers.Message;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * 作者: wangxh
 * 创建日期: 17-2-5
 * 简介: 消息处理封装, 使用适配器模式，不同功能使用不同的实现类
 */
public interface IMessageHandler {
    String handleTextMessage(Message message) throws IOException, JAXBException;
    String handleImageMessage(Message message);
    String handleVoiceMessage(Message message) throws IOException, JAXBException;
    String handleVideoMessage(Message message);
    String handleShortVideoMessage(Message message);
    String handleLocationMessage(Message message);
}
