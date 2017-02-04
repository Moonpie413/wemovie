package personal.wxh.wemovie.http.douban.request;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static final String DEFAULT_COUNT = "5";
    public static final String DEFAULT_START = "0";

    private HttpTool httpTool;
    private JSONConverter<DoubanSearchResult> jsonConverter = new JSONConverter<>();

    @Autowired
    public void setHttpTool(HttpTool httpTool) {
        this.httpTool = httpTool;
    }

    @Override
    public DoubanSearchResult search(KeyType keyType, String keyword, String count, String start) throws IOException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(keyType == KeyType.q ? "q" : "tag", keyword));
        params.add(new BasicNameValuePair("count", count));
        params.add(new BasicNameValuePair("start", start));
        return basicSearch(params);
    }

    private DoubanSearchResult basicSearch(List<NameValuePair> params) throws IOException {
        if (params != null) {
            HttpResponse response = httpTool.doGet(DoubanURL.SEARCH_URL, params, null);
            DoubanSearchResult searchResult = jsonConverter.stream2Obj(
                    response.getEntity().getContent(),
                    DoubanSearchResult.class);
            if (searchResult != null) return searchResult;
        }
        return null;
    }

}
