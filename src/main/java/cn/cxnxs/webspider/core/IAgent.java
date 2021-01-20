package cn.cxnxs.webspider.core;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * 代理接口
 * @author potatomato
 */
public interface IAgent {

    /**
     * 接收上个代理传来的事件
     * @param options 配置信息
     * @param event 数据源事件
     */
    void receive(JSONObject options,Event event) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException;

    /**
     * 输出
     * @param event 输入事件
     * @return 输出事件
     */
    JSONObject output(Event event);

}
