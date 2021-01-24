package cn.cxnxs.webspider.core.agents.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>解析器工厂</p>
 *
 * @author mengjinyuan
 * @date 2021-01-24 00:04
 **/
public class WebSiteParserFactory {

    private static final Logger logger= LoggerFactory.getLogger(WebSiteParserFactory.class);
    private static final ConcurrentHashMap<String,WebSiteContentParser> webSiteContentParserConcurrentHashMap = new ConcurrentHashMap<>();
    public static final String CONTENT_TYPE_HTML="html";
    public static final String CONTENT_TYPE_JSON="json";
    public static final String CONTENT_TYPE_XML="xml";
    public static final String CONTENT_TYPE_TEXT="text";

    public static void register(String type,WebSiteContentParser webSiteContentParser){
        logger.info("register parser,type:{},parser:{}",type,webSiteContentParser);
        webSiteContentParserConcurrentHashMap.put(type,webSiteContentParser);
    }

    public static WebSiteContentParser getParser(String type){
        return webSiteContentParserConcurrentHashMap.get(type);
    }

}
