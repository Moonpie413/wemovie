package personal.wxh.wemovie.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import personal.wxh.wemovie.config.DatabaseConfig;
import personal.wxh.wemovie.config.RootConfig;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by maroon on 17-1-13.
 * DES:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DatabaseConfig.class})
public class ButtonMapperTest {

    public static final Logger logger = LoggerFactory.getLogger(ButtonMapper.class);

    @Resource
    private ButtonMapper buttonMapper;

    @Test
    public void selectById() throws Exception {
        logger.info(buttonMapper.selectById(1).toString());
    }

    @Test
    public void selectAll() throws Exception {

    }

}