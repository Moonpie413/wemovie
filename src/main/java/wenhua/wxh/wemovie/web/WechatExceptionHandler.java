package wenhua.wxh.wemovie.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wenhua.wxh.wemovie.wechat.tasks.AccessTokenException;
import wenhua.wxh.wemovie.wechat.tasks.AccessTokenService;

/**
 * Created by maroon on 17-1-10.
 * Package wenhua.wxh.wemovie.web
 * DES: 微信异常处理类
 */
@ControllerAdvice
public class WechatExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WechatExceptionHandler.class);
    private AccessTokenService tokenService;

    @ExceptionHandler(AccessTokenException.class)
    public void accessTokenHandler() {
        logger.info("收到 accessTokenException，刷新accessToken...");
        tokenService.refreshAccessToken();
    }

    @Autowired
    public void setTokenService(AccessTokenService tokenService) {
        this.tokenService = tokenService;
    }
}
