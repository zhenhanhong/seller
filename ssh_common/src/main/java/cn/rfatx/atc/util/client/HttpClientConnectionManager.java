package cn.rfatx.atc.util.client;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * http客户端连接管理
 */
public class HttpClientConnectionManager {
    @SuppressWarnings("deprecation")
    public static HttpClient getSSLInstance(HttpClient httpClient) {
        ClientConnectionManager ccm = httpClient.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", MySSLSocketFactory.getInstance(), 443));
        httpClient = new DefaultHttpClient(ccm, httpClient.getParams());
        return httpClient;
    }

    /**
     * 读取post方法
     *
     * @param url 地址
     * @return 数据
     */
    public static HttpPost getPostMethod(String url) {
        HttpPost pmethod = new HttpPost(url);
        pmethod.addHeader("Connection", "keep-alive");
        pmethod.addHeader("Accept", "*/*");
        pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        pmethod.addHeader("Host", "mp.weixin.qq.com");
        pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
        pmethod.addHeader("Cache-Control", "max-age=0");
        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        return pmethod;
    }

    /**
     * 读取get方法
     * @param url 地址
     * @return 数据
     */
    public static HttpGet getGetMethod(String url) {
        HttpGet pmethod = new HttpGet(url);
        pmethod.addHeader("Connection", "keep-alive");
        pmethod.addHeader("Cache-Control", "max-age=0");
        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        pmethod.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/;q=0.8");
        return pmethod;
    }
}
