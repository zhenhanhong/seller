package cn.rfatx.atc.util.client;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 用于提供高效的，最新的，功能丰富的支持http协议的客户端工具包
 */
public class HttpsClient {
    public static RequestConfig requestConfig;
    public static final int CONNECT_MAX_TIMEOUT = 10000;
    public static final int READ_MAX_TIMEOUT = 30000;

    static {
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectionRequestTimeout(CONNECT_MAX_TIMEOUT);
        configBuilder.setSocketTimeout(READ_MAX_TIMEOUT);
        requestConfig = configBuilder.build();
    }

    public static String execute(String url, String paramString) throws Exception {
        HttpPost httPost = getPostMethod(url, "application/x-www-form-urlencoded;text/html;charset=utf-8");
        httPost.setConfig(requestConfig);
        httPost.setEntity(new StringEntity(paramString));
        return executePost(httPost);
    }

    public static String executeSlsy(String url, String paramString) throws Exception {
        HttpPost httPost = getPostMethod(url, "multipart/form-data;charset=utf-8");
        httPost.setConfig(requestConfig);
        httPost.setEntity(new StringEntity(paramString));
        return executePost(httPost);
    }

    /**
     * 创建加密的端口连接
     * @return 创建成功失败
     */
    private static SSLConnectionSocketFactory createSSLConnectionSocketFactory() {
        SSLConnectionSocketFactory factory = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();

//            factory = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null
//                    , SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            factory = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
                @Override
                public void verify(String s, SSLSocket sslSocket) throws IOException {

                }

                @Override
                public void verify(String s, X509Certificate x509Certificate) throws SSLException {

                }

                @Override
                public void verify(String s, String[] strings, String[] strings1) throws SSLException {

                }

                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;

    }

    /**
     * 读取post方法
     *
     * @param url 地址
     * @param contentType 读取类型
     * @return 请求方法
     */
    private static HttpPost getPostMethod(String url, String contentType) {
        HttpPost postMethod = new HttpPost(url);
        postMethod.addHeader("Connection", "keep-alive");
        postMethod.addHeader("Accept", "*/*");
        postMethod.addHeader("Cache-Control", "max-age=0");
        postMethod.addHeader("Content-Type", "application/x-www-form-urlencoded;text/html;charset=utf-8");
        return postMethod;
    }

    /**
     * 执行post方法
     *
     * @param httPost 请求方式
     * @return 数据类型
     * @throws Exception 异常
     */
    private static String executePost(HttpPost httPost) throws Exception {
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(createSSLConnectionSocketFactory()).setDefaultRequestConfig(requestConfig)
                .build();
        CloseableHttpResponse response = httpclient.execute(httPost);
        String returnStr = EntityUtils.toString(response.getEntity(), "utf-8");
        httPost.releaseConnection();
        return returnStr;
    }

}
