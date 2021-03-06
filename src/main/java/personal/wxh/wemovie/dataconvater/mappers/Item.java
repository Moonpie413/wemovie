package personal.wxh.wemovie.dataconvater.mappers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 作者: wangxh
 * 创建日期: 17-2-3
 */
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    public Item() {}

    public Item(String title) {
        Title = title;
    }

    public Item(String title, String description) {
        Title = title;
        Description = description;
    }

    public Item(String title, String description, String picUrl, String url) {
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Title;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Description;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String PicUrl;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Url;

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

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
