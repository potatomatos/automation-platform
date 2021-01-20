package cn.cxnxs.webspider.core;

import org.apache.http.cookie.Cookie;
import org.apache.http.Header;

import java.util.List;

/**
 * <p>http返回结果</p>
 *
 * @author mengjinyuan
 * @date 2021-01-20 00:19
 **/
public class HttpResult {

    /**
     * 状态码
     */
    private Integer statusCode;

    /**
     * cookie信息
     */
    private List<Cookie> cookies;

    /**
     * 返回请求头信息
     */
    private List<Header> headers;

    /**
     * 返回数据
     */
    private String data;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
