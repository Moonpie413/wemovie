package personal.wxh.wemovie.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by maroon on 17-1-8.
 * DES: HTTP 请求相关
 */
@Component
public class HttpTool {

    private static final String GET = "GET";
    private static final String POST = "POST";

    private final Logger logger = LoggerFactory.getLogger(HttpTool.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private CloseableHttpClient httpClient;

    @Autowired
    public void setCloseableHttpClient (CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Get请求返回JSON数据
     * @param url url地址
     * @param params 参数
     * @param headers 请求头
     * @return 响应JSON对象
     * @throws IOException IOException
     */
    public JsonNode doGetWithJSON(String url, List<NameValuePair> params, Header[] headers) throws IOException {
        HttpResponse response = doGet(url, params, headers);
        Header header = response.getFirstHeader("Content-Type");
        if (header.getValue() == null || "".equals(header.getValue())
                || !header.getValue().startsWith("application/json")) throw new RuntimeException("content-type无效");
        return objectMapper.readValue(response.getEntity().getContent(), JsonNode.class);
    }

    /**
     * Get请求
     * @param url url地址
     * @param params 参数
     * @param headers 请求头
     * @return 响应对象
     * @throws IOException IOException
     */
    public HttpResponse doGet(String url, List<NameValuePair> params, Header[] headers) throws IOException {
        return send(url, GET, params, headers, null);
    }

    /**
     * Post请求
     * @param url url地址
     * @param params 参数
     * @param headers 请求头
     * @return 响应对象
     * @throws IOException IOException
     */
    public HttpResponse doPost(String url, List<NameValuePair> params, Header[] headers, HttpEntity entity) throws IOException {
        return send(url, POST, params, headers, entity);
    }

    /**
     * 基本的请求方法
     * @param url 请求的url
     * @param method 方法
     * @param params 参数
     * @param headers 请求头
     * @return 响应对象
     * @throws IOException IOException
     */
    private HttpResponse send(String url, String method,
                              List<NameValuePair> params, Header[] headers, HttpEntity entity) throws IOException {
        if (!GET.equals(method) && !POST.equals(method)) throw new RuntimeException("Method not Support");
        URIBuilder builder;
        HttpResponse response = null;

        try {
            builder = new URIBuilder(url);
            builder.setCharset(Charset.forName("utf-8"));
            if (params != null) {
                builder.addParameters(params);
            }
            logger.info("发起 {} 请求： {}， 参数： {}", method, url, params == null ? "无" : params);
            if (GET.equals(method)) {
                HttpGet httpGet = new HttpGet(builder.build());
                if (headers != null) httpGet.setHeaders(headers);
                response = httpClient.execute(httpGet);
            } else {
                HttpPost httpPost = new HttpPost(builder.build());
                if (headers != null) httpPost.setHeaders(headers);
                if (entity != null) httpPost.setEntity(entity);
                response = httpClient.execute(httpPost);
            }
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                logger.info("URL： {} 请求成功", url);
            } else {
                logger.warn("URL: {} 请求失败，状态码： {}", url, statusCode);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return response;
    }

}
