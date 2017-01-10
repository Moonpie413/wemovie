package wenhua.wxh.wemovie.wechat;

/**
 * Created by maroon on 17-1-10.
 * Package wenhua.wxh.wemovie.wechat
 * DES:
 */
public class WechatException extends Exception {
    private String errorCode;

    public WechatException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
