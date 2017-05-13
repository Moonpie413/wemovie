package personal.wxh.wemovie.dataconvater;

import com.sun.xml.bind.marshaller.DataWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by maroon on 17-1-10.
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
   *
   * @return 转换后的对象
   */
  public Object xml2Object(InputStream inputStream) throws JAXBException {
    return unmarshaller.unmarshal(inputStream);
  }

  /**
   * 对象转换为XML
   */
  public String object2XML(Object o) throws IOException, JAXBException {
    String result;
    try (StringWriter stringWriter = new StringWriter();) {
      PrintWriter printWriter = new PrintWriter(stringWriter);
      DataWriter dataWriter = new DataWriter(printWriter, "UTF-8",
          (buf, start, len, isAttValue, out) -> {
            for (int i = start; i < start + len; i++) {
              char ch = buf[i];
              out.write(ch);
            }
          });
      marshaller.marshal(o, dataWriter);
      result = stringWriter.toString();
    }
    return result;
  }
}
