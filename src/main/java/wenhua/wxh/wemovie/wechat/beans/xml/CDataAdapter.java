package wenhua.wxh.wemovie.wechat.beans.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by maroon on 17-1-11.
 * Package wenhua.wxh.wemovie.wechat.beans.xml
 * DES:
 */
public class CDataAdapter extends XmlAdapter<String, String> {
    @Override
    public String unmarshal(String s) throws Exception {
        return s;
    }

    @Override
    public String marshal(String s) throws Exception {
        return "<![CDATA[" + s + "]]>";
    }
}
