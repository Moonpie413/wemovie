package personal.wxh.wemovie.config;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import personal.wxh.wemovie.dataconvater.mappers.Message;
import personal.wxh.wemovie.props.PropertyGetter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by wangxh on 17-1-7.
 * DES:
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "personal.wxh.wemovie",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = { Configuration.class, Controller.class, RestController.class, Repository.class }))
public class RootConfig {

    private PropertyGetter propertyGetter;

    @Autowired
    public void setPropertyGetter(PropertyGetter propertyGetter) {
        this.propertyGetter = propertyGetter;
    }

    @Bean(destroyMethod = "close")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        return connectionManager;
    }

    @Bean(destroyMethod = "close")
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager()).build();
    }

    @Bean
    public Unmarshaller unmarshaller() throws JAXBException {
        return jaxbContext().createUnmarshaller();
    }

    @Bean
    public Marshaller marshaller() throws JAXBException {
        Marshaller marshaller = jaxbContext().createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(CharacterEscapeHandler.class.getName(),
                new CharacterEscapeHandler() {
                    @Override
                    public void escape(char[] ac, int i, int j, boolean flag,
                                       Writer writer) throws IOException {
                        writer.write(ac, i, j);
                    }
                });
        return marshaller;
    }

    @Bean
    public JAXBContext jaxbContext() throws JAXBException {
        return JAXBContext.newInstance(Message.class);
    }


}
