package personal.wxh.wemovie.http.douban.request;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import personal.wxh.wemovie.dataconvater.JSONConverter;
import personal.wxh.wemovie.global.DoubanURL;
import personal.wxh.wemovie.http.HttpTool;
import personal.wxh.wemovie.http.douban.modules.DoubanSearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: wangxh
 * 创建日期: 17-2-4
 * 简介:
 */
@Component
public class DoubanSearchImpl implements IDoubanSearch{

    private static final Logger logger = LoggerFactory.getLogger(DoubanSearchImpl.class);
    public static final String DEFAULT_COUNT = "5";
    public static final String DEFAULT_START = "0";

    private HttpTool httpTool;
    private JSONConverter jsonConverter;

    @Autowired
    public void setHttpTool(HttpTool httpTool) {
        this.httpTool = httpTool;
    }

    @Autowired
    public void setJsonConverter(JSONConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    @Override
    @Cacheable(key = "#keyword", value = "doubanSearchCache")
    public DoubanSearchResult search(KeyType keyType, String keyword, String count, String start) {
        if (keyword != null) {
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(keyType == KeyType.q ? "q" : "tag", keyword));
            params.add(new BasicNameValuePair("count", count));
            params.add(new BasicNameValuePair("start", start));
            try {
                HttpResponse response = httpTool.doGet(DoubanURL.SEARCH_URL, params, null);
                DoubanSearchResult searchResult = (DoubanSearchResult) jsonConverter.stream2Obj(
                        response.getEntity().getContent(),
                        DoubanSearchResult.class);
                if (searchResult == null) logger.error("豆瓣搜索关键字 {} 错误", keyword);
                else logger.info("豆瓣搜索关键字 {} 成功， 返回 {} 个 结果", keyword, searchResult.getCount());
                return searchResult;
            } catch (IOException e) {
                logger.error("调用豆瓣搜索异常", e);
            }
        }
        return null;
    }

}
