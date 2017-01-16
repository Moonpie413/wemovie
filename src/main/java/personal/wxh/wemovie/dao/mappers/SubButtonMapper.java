package personal.wxh.wemovie.dao.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import personal.wxh.wemovie.dao.pojo.SubButton;
import personal.wxh.wemovie.dao.sqlbuilder.SubButtonSqlBuilder;

import java.util.List;

/**
 * Created by maroon on 17-1-16.
 * DES:
 */
public interface SubButtonMapper {

    /**
     * 根据button_id查询所有关联的sub_button
     * @param button_id
     * @return List<SubButtonMapper>
     */
    @SelectProvider(type = SubButtonSqlBuilder.class, method = "buildGetSubButtonForButton")
    List<SubButton> getSubButtonsForButton(@Param("button_id") Integer button_id);
}
