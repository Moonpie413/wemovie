package personal.wxh.wemovie.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import personal.wxh.wemovie.config.DatabaseConfig;
import personal.wxh.wemovie.config.RootConfig;

import static org.junit.Assert.*;

/**
 * Created by maroon on 17-1-16.
 * DES:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DatabaseConfig.class})
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void getMenu() throws Exception {
        menuService.getMenu();
    }

}