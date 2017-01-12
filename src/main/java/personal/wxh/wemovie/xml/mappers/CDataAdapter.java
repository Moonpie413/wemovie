package personal.wxh.wemovie.xml.mappers;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by maroon on 17-1-11.
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
