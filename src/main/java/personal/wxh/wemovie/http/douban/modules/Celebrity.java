package personal.wxh.wemovie.http.douban.modules;

import java.util.Map;

/**
 * 作者: wangxh
 * 创建日期: 17-2-4
 * 简介: 影人描述 包含演员、导演
 */
public class Celebrity {
    private Integer id; // 影人条目ID
    private String name; // 中文名
    private String alt; // 影人条目URL
    private Map<String, String> avatars; // 影人头像

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Map<String, String> getAvatars() {
        return avatars;
    }

    public void setAvatars(Map<String, String> avatars) {
        this.avatars = avatars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Celebrity celebrity = (Celebrity) o;

        if (id != null ? !id.equals(celebrity.id) : celebrity.id != null) return false;
        if (name != null ? !name.equals(celebrity.name) : celebrity.name != null) return false;
        if (alt != null ? !alt.equals(celebrity.alt) : celebrity.alt != null) return false;
        return avatars != null ? avatars.equals(celebrity.avatars) : celebrity.avatars == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (alt != null ? alt.hashCode() : 0);
        result = 31 * result + (avatars != null ? avatars.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Celebrity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alt='" + alt + '\'' +
                ", avatars=" + avatars +
                '}';
    }
}
