package personal.wxh.wemovie.props;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import personal.wxh.wemovie.config.RootConfig;

/**
 * Created by maroon on 17-1-8.
 * DES:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class PropertyUtilGetterTest {

    @Autowired
    private PropertyGetter propertyGetter;

    @Test
    public void getProperty() throws Exception {
        Assert.assertEquals("wx68b08745c5b0dee2", propertyGetter.getProperty(PropertyGetter.APPID));
    }

}