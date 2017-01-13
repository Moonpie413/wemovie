package personal.wxh.wemovie.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
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
public class SubButtonMapperTest {

    @Resource
    private SubButtonMapper subButtonMapper;

    @Test
    public void prepareSubButtonForButton() throws Exception {
        System.out.println(subButtonMapper.prepareSubButtonForButton(1));
    }

}