package personal.wxh.wemovie.dao.mappers;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import personal.wxh.wemovie.dao.pojo.Button;
import personal.wxh.wemovie.dao.sqlbuilder.ButtonSqlBuilder;

import java.util.List;

/**
 * Created by maroon on 17-1-16.
 * DES:
 */
@Repository
public interface ButtonMapper {
    /**
     * 获取所有button以及它所包含的sub_button
     * @return List<Button>
     */
    @Results({@Result(property = "button_id", id = true, column = "button_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "type", column = "type"),
            @Result(property = "key", column = "key"),
            @Result(property = "url", column = "url"),
            @Result(property = "media_id", column = "media_id"),
            @Result(property = "subButton",
                    column = "button_id",
                    many = @Many(select = "personal.wxh.wemovie.dao.mappers.SubButtonMapper.getSubButtonsForButton"))})
    @SelectProvider(type = ButtonSqlBuilder.class, method = "buildGetAllButtons")
    List<Button> getAllButtons();
}
