package personal.wxh.wemovie.dataconvater;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import personal.wxh.wemovie.config.DatabaseConfig;
import personal.wxh.wemovie.config.RootConfig;
import personal.wxh.wemovie.http.douban.modules.DoubanSearchResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * 作者: wangxh
 * 创建日期: 17-2-4
 * 简介:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DatabaseConfig.class})
public class JSONConverterTest {

    private static final Logger logger = LoggerFactory.getLogger(JSONConverterTest.class);

    private InputStream inputStream;

    @Autowired
    private
    JSONConverter jsonConverter;

    @Before
    public void setUp() throws Exception {
        Resource resource = new ClassPathResource("testRes.json");
        inputStream = new FileInputStream(resource.getFile());
    }

    @After
    public void tearDown() throws Exception {
        inputStream.close();
    }

    @Test
    public void obj2JSON() throws Exception {
        DoubanSearchResult result = (DoubanSearchResult) jsonConverter.stream2Obj(inputStream, DoubanSearchResult.class);
        String jsonResult = jsonConverter.obj2JSON(result);
        logger.info(jsonResult);
    }

    @Test
    public void jsonString2Obj() throws Exception {
    }

    @Test
    public void stream2Obj() throws Exception {
        DoubanSearchResult result = (DoubanSearchResult) jsonConverter.stream2Obj(inputStream, DoubanSearchResult.class);
        logger.info(result.toString());
    }

}