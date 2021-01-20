package cn.cxnxs.webspider.core;

import com.alibaba.fastjson.JSONObject;

/**
 * <p></p>
 *
 * @author mengjinyuan
 * @date 2021-01-21 00:25
 **/
public class HttpPostRequest extends HttpRequest{

    /**
     * 请求数据
     */
    private JSONObject postData;
    /**
     * 是否需要返回数据
     */
    private Boolean noNeedResponse;

    public JSONObject getPostData() {
        return postData;
    }

    public void setPostData(JSONObject postData) {
        this.postData = postData;
    }

    public Boolean getNoNeedResponse() {
        return noNeedResponse;
    }

    public void setNoNeedResponse(Boolean noNeedResponse) {
        this.noNeedResponse = noNeedResponse;
    }

    @Override
    public String toString() {
        return "HttpPostRequest{" +
                "postData=" + postData +
                ", noNeedResponse=" + noNeedResponse +
                "} " + super.toString();
    }
}
