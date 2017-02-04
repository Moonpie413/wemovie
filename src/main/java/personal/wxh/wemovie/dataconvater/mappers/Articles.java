package personal.wxh.wemovie.dataconvater.mappers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 作者: wangxh
 * 创建日期: 17-2-3
 */
@XmlRootElement(name = "Articles")
@XmlAccessorType(XmlAccessType.FIELD)
public class Articles {

    public Articles() {}

    public Articles(List<Item> items) {
        this.items = items;
    }

    @XmlElement(name = "item")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
