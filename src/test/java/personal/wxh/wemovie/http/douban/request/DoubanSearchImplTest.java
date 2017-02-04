package personal.wxh.wemovie.http.douban.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import personal.wxh.wemovie.config.DatabaseConfig;
import personal.wxh.wemovie.config.RootConfig;
import personal.wxh.wemovie.http.douban.modules.DoubanSearchResult;

import static org.junit.Assert.*;

/**
 * 作者: wangxh
 * 创建日期: 17-2-4
 * 简介:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DatabaseConfig.class})
public class DoubanSearchImplTest {

    @Autowired
    IDoubanSearch doubanSearch;

    @Test
    public void searchWithQueryString() throws Exception {
        String queryString = "张艺谋";
        DoubanSearchResult searchResult = doubanSearch.search(KeyType.q,
                queryString, DoubanSearchImpl.DEFAULT_COUNT, DoubanSearchImpl.DEFAULT_START);
        System.out.println(searchResult);
    }

    @Test
    public void searchWithTag() throws Exception {
        String qyeryString = "动作";
        DoubanSearchResult searchResult = doubanSearch.search(
                KeyType.tag, qyeryString, DoubanSearchImpl.DEFAULT_COUNT, DoubanSearchImpl.DEFAULT_START);
        System.out.println(searchResult);
    }

}