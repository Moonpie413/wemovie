package wenhua.wxh.wemovie.wechat.beans.xml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by maroon on 17-1-10.
 * Package wenhua.wxh.wemovie.wechat.beans
 * DES:
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    @XmlElement(required = true)
    private String ToUserName;
    @XmlElement(required = true)
    private String FromUserName;
    @XmlElement(required = true)
    private String CreateTime;
    @XmlElement(required = true)
    private String MsgType;
    @XmlElement(required = true)
    private String MsgId;

    @XmlElement
    private String Content;

    @XmlElement
    private String PicUrl;
    // 语音格式，如amr，speex等
    @XmlElement
    private String Format;
    @XmlElement
    private String MediaId;
    // 视频消息缩略图的媒体id
    @XmlElement
    private String ThumbMediaId;

    // 地理位置
    @XmlElement
    private String Location_X;

    @XmlElement
    private String Location_Y;

    // 地图缩放大小
    @XmlElement
    private String Scale;

    // 地理位置信息
    @XmlElement
    private String Label;

    @XmlElement
    private String Title;

    @XmlElement
    private String Description;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return new EqualsBuilder()
                .append(CreateTime, message.CreateTime)
                .append(MsgId, message.MsgId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(CreateTime)
                .append(MsgId)
                .toHashCode();
    }

    // TODO toString转为xml
//    @Override
//    public String toString() {
//        Field[] fields = this.getClass().getDeclaredFields();
//        StringBuilder xmlString = new StringBuilder();
//        xmlString.append("<xml>");
//        for (Field field : fields) {
//            String fieldName = field.getName();
//            try {
//                Method method = this.getClass().getMethod("get" + fieldName);
//                String fieldValue = (String) method.invoke(this);
//                if (!fieldValue.equals("null")) {
//
//                }
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        xmlString.append("</xml>")
//        return null;
//    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ToUserName", ToUserName)
                .append("FromUserName", FromUserName)
                .append("CreateTime", CreateTime)
                .append("MsgType", MsgType)
                .append("MsgId", MsgId)
                .append("Content", Content)
                .append("PicUrl", PicUrl)
                .append("Format", Format)
                .append("MediaId", MediaId)
                .append("ThumbMediaId", ThumbMediaId)
                .append("Location_X", Location_X)
                .append("Location_Y", Location_Y)
                .append("Scale", Scale)
                .append("Label", Label)
                .append("Title", Title)
                .append("Description", Description)
                .toString();
    }
}
