package cn.cxnxs.webspider.core.agents.parser;

import cn.cxnxs.webspider.core.http.ContentType;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * <p>XML解析器</p>
 *
 * @author mengjinyuan
 * @date 2021-02-01 22:38
 **/
public class TextParser extends WebSiteContentParser {

    public TextParser(ContentType contentType, String name) {
        super(contentType, name);
    }

    @Override
    public List<Map<String, String>> parse(JSONObject extract, String payload) {
        return null;
    }
}
