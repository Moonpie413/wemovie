package wenhua.wxh.wemovie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wangxh on 17-1-7.
 * Package wenhua.wxh.wemovie.config
 * DES:
 */
@Configuration
@EnableWebMvc
@ComponentScan("wenhua.wxh.wemovie.web")
public class ServletConfig extends WebMvcConfigurerAdapter {

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }


}
