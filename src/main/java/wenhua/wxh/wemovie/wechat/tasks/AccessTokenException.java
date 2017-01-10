package wenhua.wxh.wemovie.wechat.tasks;

import wenhua.wxh.wemovie.wechat.WechatException;

/**
 * Created by maroon on 17-1-10.
 * Package wenhua.wxh.wemovie.wechat.tasks
 * DES:
 */
public class AccessTokenException extends WechatException {
    public AccessTokenException(String message, String errorCode) {
        super(message, errorCode);
    }
}
