package cn.cxnxs.webspider.core.agents;

import cn.cxnxs.webspider.core.Event;
import cn.cxnxs.webspider.core.IAgent;
import cn.cxnxs.webspider.utils.HttpRequestUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>网站代理</p>
 *
 * @author mengjinyuan
 * @date 2021-01-18 14:02
 **/
public class WebsiteAgent implements IAgent {
    private static final Logger logger= LoggerFactory.getLogger(WebsiteAgent.class);
    @Override
    public void receive(JSONObject options, Event event) throws IOException {
        //请求地址
        String url=options.getString("url");
        HttpRequestUtil.httpGet(url);
    }

    @Override
    public JSONObject output(Event event) {
        return null;
    }

}
