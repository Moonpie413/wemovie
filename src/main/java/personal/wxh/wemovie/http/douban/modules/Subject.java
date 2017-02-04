package personal.wxh.wemovie.http.douban.modules;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * 作者: wangxh
 * 创建日期: 17-2-4
 * 简介:
 */
public class Subject {

    // 条目id
    @JsonProperty
    private Integer id;

    // 评分类
    @JsonProperty("rating")
    private Rating rating;

    // 影片类型 最多三个
    @JsonProperty("genres")
    private List<String> genres;

    // 标题
    @JsonProperty
    private String title;

    // 演员列表
    @JsonProperty("casts")
    private List<Celebrity> casts;

    // 看过人数
    @JsonProperty
    private Integer collect_count;

    // 原始标题
    @JsonProperty
    private String original_title;

    // 条目分类
    @JsonProperty
    private String subtype;

    // 导演列表
    @JsonProperty("directors")
    private List<Celebrity> directors;

    // 年份
    @JsonProperty
    private String year;

    // 图片
    @JsonProperty
    private Map<String, String> images;

    // 豆瓣链接
    @JsonProperty
    private String alt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Celebrity> getCasts() {
        return casts;
    }

    public void setCasts(List<Celebrity> casts) {
        this.casts = casts;
    }

    public Integer getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(Integer collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<Celebrity> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Celebrity> directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", rating=" + rating +
                ", genres=" + genres +
                ", title='" + title + '\'' +
                ", casts=" + casts +
                ", collect_count=" + collect_count +
                ", original_title='" + original_title + '\'' +
                ", subtype='" + subtype + '\'' +
                ", directors=" + directors +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                '}';
    }
}
