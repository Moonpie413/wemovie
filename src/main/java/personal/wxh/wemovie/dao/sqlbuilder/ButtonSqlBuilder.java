package personal.wxh.wemovie.dao.sqlbuilder;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by maroon on 17-1-16.
 * DES:
 */
public class ButtonSqlBuilder {
    private static final String ALL_COLUMNS = "`button_id`, `name`, `type`, `key`, `url`, `media_id`";
    private static final String TABLE_NAME = "button";

    public String buildGetAllButtons() {
        return new SQL() {{
            SELECT(ALL_COLUMNS);
            FROM(TABLE_NAME);
        }}.toString();
    }
}
