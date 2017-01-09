package wenhua.wxh.wemovie.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wenhua.wxh.wemovie.utils.PropertyUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangxh on 17-1-7.
 * Package wenhua.wxh.wemovie.web
 * DES: 微信验证控制器类
 */
@RestController
@RequestMapping("/auth")
public class WeAuthController {

    private PropertyUtil propertyUtil;
    private Logger logger = LoggerFactory.getLogger(WeAuthController.class);

    @Autowired
    private void setPropertyUtil(PropertyUtil propertyUtil) {
        this.propertyUtil = propertyUtil;
    }

    @GetMapping
    public String auth(@RequestParam String signature, @RequestParam String timestamp,
                       @RequestParam String nonce, @RequestParam String echostr) {
        String token = propertyUtil.getProperty(PropertyUtil.TOKEN);
        List<String> list = Arrays.asList(token, nonce, timestamp);
        Collections.sort(list);
        String mySignature = DigestUtils.sha1Hex(list.get(0) + list.get(1) + list.get(2));
        if (mySignature.equals(signature)) {
            logger.info("wechat auth succeed");
            return echostr;
        }
        logger.info("wechat auth failed");
        return "bad request";
    }
}
