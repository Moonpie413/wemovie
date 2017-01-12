package personal.wxh.wemovie.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import personal.wxh.wemovie.http.wechat.token.HttpAccessToken;
import personal.wxh.wemovie.service.AccessTokenService;

/**
 * Created by maroon on 17-1-9.
 * DES: 自动刷新Token
 */
@Service
public class AccessTokenTask {
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenTask.class);
    private static final int EXPIRES_IN_DEFAULT = 7200;

    private AccessTokenService tokenService;
    /**
     * 每隔 fixedRate / 1000 s 刷新accessToken
     * 微信要求为7200刷新一次
     */
    @Scheduled(fixedRate = (EXPIRES_IN_DEFAULT - 200) * 1000)
    public void refreshAccessToken() {
        tokenService.refreshAccessToken();
    }

    @Autowired
    public void setTokenService(AccessTokenService tokenService) {
        this.tokenService = tokenService;
    }

}
