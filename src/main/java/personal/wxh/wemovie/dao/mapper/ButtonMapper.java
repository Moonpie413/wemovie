package personal.wxh.wemovie.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import personal.wxh.wemovie.dao.pojo.Button;

import java.util.List;

/**
 * Created by maroon on 17-1-13.
 * DES:
 */
@Mapper
@Repository
public interface ButtonMapper {

    public static final String COLUMNS = "*";

    @Select("SELECT " + COLUMNS + " FROM button WHERE button_id = #{button_id}")
    @Results(value = {@Result(property = "button_id", column = "button_id"),
            @Result(property = "name", column = "type"),
            @Result(property = "key", column = "key"),
            @Result(property = "url", column = "url"),
            @Result(property = "media_id", column = "media_id"),
            @Result(property = "subButtonList", javaType = List.class, column = "button_id",
                    many = @Many(select = "personal.wxh.wemovie.dao.mapper.SubButtonMapper.prepareSubButtonForButton"))})
    Button selectById(@Param("button_id") Integer button_id);

    @Select("SELECT " + COLUMNS + " FROM button")
    List<Button> selectAll();
}
