package personal.wxh.wemovie.dataconvater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * 作者: wangxh
 * 创建日期: 17-2-4
 * 简介: JSON转换器
 */
@Component
public class JSONConverter {
    private static final Logger logger = LoggerFactory.getLogger(JSONConverter.class);

    private ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * 对象转换为JSON字符串
     * @param object 对象
     * @return JSON字符串
     * @throws JsonProcessingException
     */
    public String obj2JSON(Object object) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(object);
    }

    /**
     * JSON字符串转成对象
     * @param jsonString JSON字符串
     * @param type 对象的class类型
     * @return 对象
     * @throws IOException
     */
    public Object jsonString2Obj(String jsonString, Class type) throws IOException {
        return jsonMapper.readValue(jsonString, type);
    }

    /**
     * 包含JSON的流转换为对象
     * @param in 输入流
     * @return 对象
     * @throws IOException
     */
    public Object stream2Obj(InputStream in, Class type) throws IOException {
        return jsonMapper.readValue(in, type);
    }

}
