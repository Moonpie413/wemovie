package personal.wxh.wemovie.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import personal.wxh.wemovie.config.DatabaseConfig;
import personal.wxh.wemovie.config.RootConfig;

/**
 * Created by maroon on 17-1-13.
 * DES:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DatabaseConfig.class})
public class MenuServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
    private MenuService menuService;

    @Test
    public void getMenuInfo() throws Exception {
        logger.info(menuService.getMenuInfo());
    }

    @Test
    public void selectAll() throws Exception {
        logger.info(menuService.getMenuInfo());
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}