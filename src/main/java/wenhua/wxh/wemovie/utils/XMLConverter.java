package wenhua.wxh.wemovie.utils;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wenhua.wxh.wemovie.wechat.beans.xml.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by maroon on 17-1-10.
 * Package wenhua.wxh.wemovie.utils
 * DES:
 */
@Component
public class XMLConverter {

    private static final Logger logger = LoggerFactory.getLogger(XMLConverter.class);

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @Autowired
    public XMLConverter(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    /**
     * XML输入流转为对象
     * @param inputStream
     * @return 转换后的对象
     * @throws JAXBException
     */
    public Object xml2Object(InputStream inputStream) throws JAXBException {
        return unmarshaller.unmarshal(inputStream);
    }

    /**
     * 对象转换为XML
     * @param o
     * @throws IOException
     * @throws JAXBException
     */
    public String object2XML(Object o) throws IOException, JAXBException {
        String result;
        try (StringWriter stringWriter = new StringWriter()){
            marshaller.marshal(o, stringWriter);
            result = stringWriter.toString();
        }
        return result;
    }
}
