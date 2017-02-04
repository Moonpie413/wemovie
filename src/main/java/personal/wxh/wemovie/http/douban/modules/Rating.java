package personal.wxh.wemovie.http.douban.modules;

/**
 * 作者: wangxh
 * 创建日期: 17-2-4
 * 简介: 评分类
 */
public class Rating {
    // 最高评分
    private Integer max;

    // 平均评分
    private Float average;

    // 点赞数
    private String stars;

    // 最低评分
    private Integer min;

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (max != null ? !max.equals(rating.max) : rating.max != null) return false;
        if (average != null ? !average.equals(rating.average) : rating.average != null) return false;
        if (stars != null ? !stars.equals(rating.stars) : rating.stars != null) return false;
        return min != null ? min.equals(rating.min) : rating.min == null;
    }

    @Override
    public int hashCode() {
        int result = max != null ? max.hashCode() : 0;
        result = 31 * result + (average != null ? average.hashCode() : 0);
        result = 31 * result + (stars != null ? stars.hashCode() : 0);
        result = 31 * result + (min != null ? min.hashCode() : 0);
        return result;
    }
}
