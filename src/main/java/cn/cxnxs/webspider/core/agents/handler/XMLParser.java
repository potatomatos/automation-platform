package cn.cxnxs.webspider.core.agents.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>XML解析器</p>
 *
 * @author mengjinyuan
 * @date 2021-02-01 22:38
 **/
@Component
public class XMLParser implements InitializingBean, WebSiteContentParser {
    @Override
    public List<Map<String, String>> parse(JSONObject extract, String payload) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
