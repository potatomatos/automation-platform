package cn.cxnxs.webspider.core.agents.handler;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 网页内容处理器
 * @author potatomato
 */
public interface WebSiteContentParser {


    /**
     * 根据配置解析网站内容
     * @param extract 提取配置
     * @param payload 提取内容
     * @return 解析成列表返回
     */
    List<Map<String,String>> parse(JSONObject extract,String payload);
}
