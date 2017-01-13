package personal.wxh.wemovie.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import personal.wxh.wemovie.dao.pojo.SubButton;

import java.util.List;

/**
 * Created by maroon on 17-1-13.
 * DES:
 */
@Mapper
@Repository
public interface SubButtonMapper {
//    public static final String COLUMNS = "`id`, `name`, `type`, `key`, `url`, `media_id`";
    public static final String COLUMNS = "*";

    @Select("SELECT " + COLUMNS + " FROM sub_button WHERE button_id = #{button_id}")
    List<SubButton> prepareSubButtonForButton(@Param("button_id") Integer button_id);
}
