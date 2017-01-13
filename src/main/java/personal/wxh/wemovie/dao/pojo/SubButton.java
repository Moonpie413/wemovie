package personal.wxh.wemovie.dao.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by maroon on 17-1-13.
 * DES:
 */
public class SubButton {
    private Integer sub_button_id;
    private Integer button_id;
    private String name;
    private String type;
    private String key;
    private String url;
    private String media_id;

    public Integer getSub_button_id() {
        return sub_button_id;
    }

    public void setSub_button_id(Integer sub_button_id) {
        this.sub_button_id = sub_button_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public Integer getButton_id() {
        return button_id;
    }

    public void setButton_id(Integer button_id) {
        this.button_id = button_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SubButton subButton = (SubButton) o;

        return new EqualsBuilder()
                .append(sub_button_id, subButton.sub_button_id)
                .append(button_id, subButton.button_id)
                .append(name, subButton.name)
                .append(type, subButton.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sub_button_id)
                .append(button_id)
                .append(name)
                .append(type)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sub_button_id", sub_button_id)
                .append("button_id", button_id)
                .append("name", name)
                .append("type", type)
                .append("key", key)
                .append("url", url)
                .append("media_id", media_id)
                .toString();
    }
}
