package personal.wxh.wemovie.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import personal.wxh.wemovie.dao.mappers.ButtonMapper;
import personal.wxh.wemovie.dao.pojo.Button;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<Button>> buttonMap = new HashMap<>();
        buttonMap.put("button", buttonMapper.getAllButtons());
        String result = null;
        try {
            result = objectMapper.writeValueAsString(buttonMap);
        } catch (JsonProcessingException e) {
            logger.error("button转Json异常", e);
        }
        return result;
    }
}
