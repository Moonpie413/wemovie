package personal.wxh.wemovie.dao.sqlbuilder;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by maroon on 17-1-16.
 * DES:
 */
public class SubButtonSqlBuilder {

    private static final String ALL_COLUMNS = "`sub_button_id`, `button_id`, `name`, `type`, `key`, `url`, `media_id`";
    private static final String TABLE_NAME = "sub_button";

    /**
     * 构建根据button_id查询所有关联的sub_button的sql语句
     * @param button_id
     * @return
     */
    public String buildGetSubButtonForButton(@Param("button_id") Integer button_id) {
        return new SQL() {{
            SELECT(ALL_COLUMNS);
            FROM(TABLE_NAME);
            WHERE("button_id = #{button_id}");
        }}.toString();
    }
}
