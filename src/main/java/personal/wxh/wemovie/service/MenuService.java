package personal.wxh.wemovie.service;

import org.springframework.stereotype.Service;
import personal.wxh.wemovie.dao.mapper.ButtonMapper;

import javax.annotation.Resource;

/**
 * Created by maroon on 17-1-13.
 * DES:
 */
@Service
public class MenuService {

    @Resource
    private ButtonMapper buttonMapper;

    public String getMenuInfo() {
        return buttonMapper.selectAll().toString();
    }
}
