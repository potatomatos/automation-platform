package cn.cxnxs.webspider.utils;

import cn.cxnxs.webspider.core.HttpGetRequest;
import cn.cxnxs.webspider.core.HttpPostRequest;
import cn.cxnxs.webspider.core.HttpRequest;
import cn.cxnxs.webspider.core.HttpResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

/**
 * http请求工具类
 *
 * @author potatomato
 */
public class HttpRequestUtil {


    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    /**
     * post请求
     *
     * @param httpRequest 请求参数
     * @return none
     */
    public static HttpResult httpPost(HttpPostRequest httpRequest) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpResult httpResult = new HttpResult();
        CookieStore httpCookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient;
        if (httpRequest.getDisableSslVerification()) {
            httpClient = createSSLClientDefault();
        } else {
            httpClient = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore).build();
        }
        String url = httpRequest.getUrl();
        HttpPost method = new HttpPost(url);
        try {
            //设置请求头信息
            method.setHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36 SE 2.X MetaSr 1.0"));
            if (httpRequest.getHeaders() != null) {
                for (String key : httpRequest.getHeaders().keySet()) {
                    method.setHeader(key, httpRequest.getHeaders().getString(key));
                }
            }
            //设置post数据
            if (null != httpRequest.getPostData()) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(httpRequest.getPostData().toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                method.setEntity(entity);
            }
            url = URLDecoder.decode(url, "UTF-8");
            logger.info("------请求信息------");
            logger.info("url：{}", url);
            logger.info("请求方法：{}", method.getMethod());
            logger.info("header:{}", JSON.toJSONString(httpRequest.getHeaders(), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat));
            logger.info("请求数据：{}", JSON.toJSONString(httpRequest.getPostData(), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat));
            HttpResponse result = httpClient.execute(method);
            /*请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    if (httpRequest.getNoNeedResponse()) {
                        return null;
                    }
                    Header[] allHeaders = result.getAllHeaders();
                    List<Cookie> cookies = httpCookieStore.getCookies();

                    /*读取服务器返回过来的字符串数据**/
                    String data = EntityUtils.toString(result.getEntity());
                    httpResult.setCookies(cookies);
                    httpResult.setHeaders(Arrays.asList(allHeaders));
                    httpResult.setData(data);
                    httpResult.setStatusCode(result.getStatusLine().getStatusCode());
                    logger.info("------返回结果------");
                    logger.info("状态码：{}", httpResult.getStatusCode());
                    logger.debug("返回数据：{}", httpResult.getData());
                } catch (Exception e) {
                    logger.error("post请求提交失败,url:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败,url:" + url, e);
        }
        return httpResult;
    }

    /**
     * 发送get请求
     *
     * @param getRequest 请求参数
     * @return none
     */
    public static HttpResult httpGet(HttpGetRequest getRequest) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        //get请求返回结果
        HttpResult httpResult = new HttpResult();
        CookieStore httpCookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient;
        if (getRequest.getDisableSslVerification()) {
            httpClient = createSSLClientDefault();
        } else {
            httpClient = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore).build();
        }
        String url = getRequest.getUrl();
        HttpGet request = new HttpGet(url);
        try {
            //设置请求头信息
            request.setHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36 SE 2.X MetaSr 1.0"));
            if (getRequest.getHeaders() != null) {
                for (String key : getRequest.getHeaders().keySet()) {
                    request.setHeader(key, getRequest.getHeaders().getString(key));
                }
            }
            url = URLDecoder.decode(url, "UTF-8");
            logger.info("------请求信息------");
            logger.info("url：{}", url);
            logger.info("请求方法：{}", request.getMethod());
            logger.info("header:{}", JSON.toJSONString(getRequest.getHeaders(), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat));
            //发送get请求
            HttpResponse response = httpClient.execute(request);
            /*请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /*读取服务器返回过来的json字符串数据**/
                Header[] header = response.getAllHeaders();
                List<Cookie> cookies = httpCookieStore.getCookies();
                /*读取服务器返回过来的字符串数据**/
                String data = EntityUtils.toString(response.getEntity());
                httpResult.setCookies(cookies);
                httpResult.setHeaders(Arrays.asList(header));
                httpResult.setData(data);
                httpResult.setStatusCode(response.getStatusLine().getStatusCode());
                logger.info("------返回结果------");
                logger.info("状态码：{}", httpResult.getStatusCode());
                logger.debug("返回数据：{}", httpResult.getData());
            } else {
                logger.error("get请求失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求失败:" + url, e);
        }
        return httpResult;
    }

    private static CloseableHttpClient createSSLClientDefault() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        //信任所有
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
}
