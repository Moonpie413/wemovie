package wenhua.wxh.wemovie.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamSource;
import java.io.*;
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

    public Object xml2Object(InputStream inputStream) throws IOException {
        return unmarshaller.unmarshal(new StreamSource(inputStream));
    }
}
