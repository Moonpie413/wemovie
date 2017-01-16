package personal.wxh.wemovie.dao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by maroon on 17-1-13.
 * DES:
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Button {
    @JsonIgnore
    private Integer button_id;
    private String name;
    private String type;
    private String key;
    private String url;
    private String media_id;
    private List<SubButton> subButton;

    public Integer getButton_id() {
        return button_id;
    }

    public void setButton_id(Integer button_id) {
        this.button_id = button_id;
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

    public List<SubButton> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<SubButton> subButton) {
        this.subButton = subButton;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Button{");
        sb.append("button_id=").append(button_id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", key='").append(key).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", media_id='").append(media_id).append('\'');
        sb.append(", subButton=").append(subButton);
        sb.append('}');
        return sb.toString();
    }
}
