package wenhua.wxh.wemovie.props;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wenhua.wxh.wemovie.config.RootConfig;
import wenhua.wxh.wemovie.utils.PropertyUtil;

/**
 * Created by maroon on 17-1-8.
 * Package wenhua.wxh.wemovie.props
 * DES:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class PropertyUtilUtilTest {

    @Autowired
    private PropertyUtil propertyUtil;

    @Test
    public void getProperty() throws Exception {
        Assert.assertEquals("wx68b08745c5b0dee2", propertyUtil.getProperty(PropertyUtil.APPID));
    }

}