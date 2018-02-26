package cn.rfatx.atc.util.client;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 微信http客户端
 */
public class WxHttpClient {
    public static DefaultHttpClient httpclient;

    static {
        httpclient = new DefaultHttpClient();
        httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
    }

    public static synchronized String execute(String url, String paramString) throws IOException {
        HttpPost httPost = HttpClientConnectionManager.getPostMethod(url);
        httPost.setEntity(new StringEntity(paramString));
        HttpResponse response = WxHttpClient.httpclient.execute(httPost);
        String returnStr = EntityUtils.toString(response.getEntity(), "utf-8");
        httPost.releaseConnection();
        return returnStr;
    }
}
