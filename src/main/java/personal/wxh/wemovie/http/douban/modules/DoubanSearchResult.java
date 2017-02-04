package personal.wxh.wemovie.http.douban.modules;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 作者: wangxh
 * 创建日期: 17-2-4
 * 简介: 豆瓣搜索结果的封装
 */
public class DoubanSearchResult {
    // 返回的最大结果数
    @JsonProperty
    private Integer count;

    // 返回结果的开始数
    @JsonProperty
    private Integer start;

    // 返回结果的实际数目
    @JsonProperty
    private Integer total;

    // 搜索结果列表
    @JsonProperty
    private List<Subject> subjects;

    // 标题
    @JsonProperty
    private String title;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoubanSearchResult that = (DoubanSearchResult) o;

        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (subjects != null ? !subjects.equals(that.subjects) : that.subjects != null) return false;
        return title != null ? title.equals(that.title) : that.title == null;
    }

    @Override
    public int hashCode() {
        int result = count != null ? count.hashCode() : 0;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (subjects != null ? subjects.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DoubanSearchResult{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", subjects=" + subjects +
                ", title='" + title + '\'' +
                '}';
    }
}
