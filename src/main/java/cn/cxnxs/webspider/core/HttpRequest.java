package cn.cxnxs.webspider.core;

import com.alibaba.fastjson.JSONObject;

/**
 * <p></p>
 *
 * @author mengjinyuan
 * @date 2021-01-20 18:28
 **/
public class HttpRequest {
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求头信息
     */
    private JSONObject headers;

    /**
     * 关闭ssl校验
     */
    private Boolean disableSslVerification=false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JSONObject getHeaders() {
        return headers;
    }

    public void setHeaders(JSONObject headers) {
        this.headers = headers;
    }


    public Boolean getDisableSslVerification() {
        return disableSslVerification;
    }

    public void setDisableSslVerification(Boolean disableSslVerification) {
        this.disableSslVerification = disableSslVerification;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "url='" + url + '\'' +
                ", headers=" + headers +
                ", disableSslVerification=" + disableSslVerification +
                '}';
    }
}
