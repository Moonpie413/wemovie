package personal.wxh.wemovie.dataconvater.mappers;

import com.sun.xml.internal.txw2.annotation.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by maroon on 17-1-10.
 * DES:
 * PS: @XmlCDATA 注解没卵用， 要生成CDATA得用 @XmlJavaTypeAdapter
 */
@XmlRootElement(name = "dataconvater")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {

    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";
    public static final String SHORTVIDEO = "shortvideo";
    public static final String LOCATION = "location";
    public static final String NEWS = "news";

    // 基本值
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String ToUserName;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String FromUserName;

    @XmlElement(required = true)
    private Long CreateTime;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String MsgType;

    @XmlElement(required = true)
    private Long MsgId;

    @XmlCDATA
    public String getToUserName() {
        return ToUserName;
    }

    @XmlCDATA
    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    @XmlCDATA
    public String getFromUserName() {
        return FromUserName;
    }

    @XmlCDATA
    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    @XmlCDATA
    public String getMsgType() {
        return MsgType;
    }

    @XmlCDATA
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }
    // 基本值结束

    // 文字消息类型
    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Content;

    @XmlCDATA
    public String getContent() {
        return Content;
    }

    @XmlCDATA
    public void setContent(String content) {
        Content = content;
    }
    // 文字消息类型结束

    // 多媒体类型
    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String PicUrl;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String MediaId;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Format;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Recognition;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String ThumbMediaId;

    @XmlCDATA
    public String getPicUrl() {
        return PicUrl;
    }

    @XmlCDATA
    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    @XmlCDATA
    public String getMediaId() {
        return MediaId;
    }

    @XmlCDATA
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @XmlCDATA
    public String getFormat() {
        return Format;
    }

    @XmlCDATA
    public void setFormat(String format) {
        Format = format;
    }

    @XmlCDATA
    public String getRecognition() {
        return Recognition;
    }

    @XmlCDATA
    public void setRecognition(String recognition) {
        Recognition = recognition;
    }

    @XmlCDATA
    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    @XmlCDATA
    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
    // 多媒体类型结束

    // 地理位置类型
    @XmlElement
    private Float Location_X;

    @XmlElement
    private Float Location_Y;

    @XmlElement
    private Integer Scale;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Label;

    public Float getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(Float location_X) {
        Location_X = location_X;
    }

    public Float getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(Float location_Y) {
        Location_Y = location_Y;
    }

    public Integer getScale() {
        return Scale;
    }

    public void setScale(Integer scale) {
        Scale = scale;
    }

    @XmlCDATA
    public String getLabel() {
        return Label;
    }

    @XmlCDATA
    public void setLabel(String label) {
        Label = label;
    }
    // 地理位置类型结束

    // 链接消息类型
    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Title;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Description;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Url;

    @XmlCDATA
    public String getTitle() {
        return Title;
    }

    @XmlCDATA
    public void setTitle(String title) {
        Title = title;
    }

    @XmlCDATA
    public String getDescription() {
        return Description;
    }

    @XmlCDATA
    public void setDescription(String description) {
        Description = description;
    }

    @XmlCDATA
    public String getUrl() {
        return Url;
    }

    @XmlCDATA
    public void setUrl(String url) {
        Url = url;
    }
    // 链接消息结束

    // 图文消息
    @XmlElement
    private Integer ArticleCount;
    @XmlElement(name = "Articles")
    private Articles articles;

    public Integer getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }
    // 图文消息结束
}
