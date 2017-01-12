package personal.wxh.wemovie.web;

/**
 * Created by maroon on 17-1-10.
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
