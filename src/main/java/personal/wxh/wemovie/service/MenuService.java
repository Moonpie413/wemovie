package personal.wxh.wemovie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import personal.wxh.wemovie.dao.mappers.ButtonMapper;

import javax.annotation.Resource;

/**
 * Created by maroon on 17-1-16.
 * DES:
 */
@Service
public class MenuService {

    public static final Logger logger = LoggerFactory.getLogger(MenuService.class);

    @Resource
    private ButtonMapper buttonMapper;

    /**
     * 根据数据库中的配置生成菜单
     * @return MenuString
     */
    public String getMenu() {
        logger.info(buttonMapper.getAllButtons().toString());
        return null;
    }
}
