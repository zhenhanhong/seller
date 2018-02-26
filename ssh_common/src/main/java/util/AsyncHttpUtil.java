package util;

import cn.rfatx.core.diva.mapper.JsonMapper;
import cn.rfatx.core.diva.web.MediaTypes;
import org.apache.commons.beanutils.BeanMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rf-author on 2017/03/02.
 */
public class AsyncHttpUtil {

    /**
     * 设置信任自定义的证书
     *
     * @param keyStorePath 密钥库路径
     * @param keyStorepass 密钥库密码
     * @return 自定义的证书
     */
    public static SSLContext custom(String keyStorePath, String keyStorepass) {
        SSLContext sc = null;
        FileInputStream instream = null;
        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            instream = new FileInputStream(new File(keyStorePath));
            trustStore.load(instream, keyStorepass.toCharArray());
            // 相信自己的CA和所有自签名的证书
            sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | KeyManagementException e) {
            e.printStackTrace();
        } finally {
            try {
                if(instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
            }
        }
        return sc;
    }

    /**
     * 绕过验证
     *
     * @return 绕过验证
     * @throws NoSuchAlgorithmException 异常
     * @throws KeyManagementException 异常
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }

    /**
     * 设置代理
     *
     * @param hostOrIP hostOrIP
     * @param port port
     * @return 设置代理
     */
    public static HttpAsyncClientBuilder proxy(String hostOrIP, int port) {
        // 依次是代理地址，代理端口号，协议类型
        HttpHost proxy = new HttpHost(hostOrIP, port, "http");
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        return HttpAsyncClients.custom().setRoutePlanner(routePlanner);
    }

    /**
     * 模拟请求
     *
     * @param url      资源地址
     * @param map      参数列表
     * @param encoding 编码
     * @param handler  结果处理类
     * @throws NoSuchAlgorithmException 异常
     * @throws KeyManagementException 异常
     * @throws IOException 异常
     * @throws ClientProtocolException 异常
     */
    public static void send(String url, Map<String, String> map, final String encoding, final AsynHttpCallBack handler) throws KeyManagementException, NoSuchAlgorithmException, ClientProtocolException, IOException {
        //绕过证书验证，处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy>create()
                .register("http", NoopIOSessionStrategy.INSTANCE)
                .register("https", new SSLIOSessionStrategy(sslcontext))
                .build();
        //配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(Runtime.getRuntime().availableProcessors()).build();
        //设置连接池大小
        ConnectingIOReactor ioReactor;
        ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor, null, sessionStrategyRegistry, null);

        //创建自定义的httpclient对象
        final CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();

        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        //装填参数
        StringEntity stringEntity = new StringEntity(JsonMapper.nonEmptyMapper().toJson(map));

        //设置参数到请求对象中
        httpPost.setEntity(stringEntity);


        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        // Start the client
        client.start();
        //执行请求操作，并拿到结果（异步）
        client.execute(httpPost, new FutureCallback<HttpResponse>() {

            @Override
            public void failed(Exception ex) {
                System.out.println(ex.fillInStackTrace());
                handler.failed(ex);
                close(client);
            }

            @Override
            public void completed(HttpResponse resp) {
                String body = "";
                //这里使用EntityUtils.toString()方式时会大概率报错，原因：未接受完毕，链接已关
                try {
                    HttpEntity entity = resp.getEntity();
                    if (entity != null) {
                        final InputStream instream = entity.getContent();
                        try {
                            final StringBuilder sb = new StringBuilder();
                            final char[] tmp = new char[1024];
                            final Reader reader = new InputStreamReader(instream, encoding);
                            int l;
                            while ((l = reader.read(tmp)) != -1) {
                                sb.append(tmp, 0, l);
                            }
                            body = sb.toString();
                        } finally {
                            instream.close();
                            EntityUtils.consume(entity);
                        }
                    }
                } catch (ParseException | IOException e) {
                    e.printStackTrace();
                }
                handler.completed(body);
                close(client);
            }

            @Override
            public void cancelled() {
                handler.cancelled();
                close(client);
            }
        });
    }

    /**
     * 模拟请求
     *
     * @param url      资源地址
     * @param param      参数列表
     * @param encoding 编码
     * @param handler  结果处理类
     * @throws NoSuchAlgorithmException 异常
     * @throws KeyManagementException 异常
     * @throws IOException 异常
     * @throws ClientProtocolException 异常
     */
    private static void send(String url, Object param, final String encoding, final AsynHttpCallBack handler) throws KeyManagementException, NoSuchAlgorithmException, ClientProtocolException, IOException {
        //绕过证书验证，处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy>create()
                .register("http", NoopIOSessionStrategy.INSTANCE)
                .register("https", new SSLIOSessionStrategy(sslcontext))
                .build();
        //配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(Runtime.getRuntime().availableProcessors()).build();
        //设置连接池大小
        ConnectingIOReactor ioReactor;
        ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor, null, sessionStrategyRegistry, null);

        //创建自定义的httpclient对象
        final CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();

        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        //装填参数
        StringEntity stringEntity = new StringEntity(new String(JsonMapper.nonEmptyMapper().toJson(param).getBytes(Charset.forName("UTF-8")), Charset.forName("ISO-8859-1")));

        //设置参数到请求对象中
        httpPost.setEntity(stringEntity);


        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", MediaTypes.JSON_UTF_8);
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        // Start the client
        client.start();
        //执行请求操作，并拿到结果（异步）
        client.execute(httpPost, new FutureCallback<HttpResponse>() {

            @Override
            public void failed(Exception ex) {
                System.out.println(ex.fillInStackTrace());
                handler.failed(ex);
                close(client);
            }

            @Override
            public void completed(HttpResponse resp) {
                String body = "";
                //这里使用EntityUtils.toString()方式时会大概率报错，原因：未接受完毕，链接已关
                try {
                    HttpEntity entity = resp.getEntity();
                    if (entity != null) {
                        final InputStream instream = entity.getContent();
                        try {
                            final StringBuilder sb = new StringBuilder();
                            final char[] tmp = new char[1024];
                            final Reader reader = new InputStreamReader(instream, encoding);
                            int l;
                            while ((l = reader.read(tmp)) != -1) {
                                sb.append(tmp, 0, l);
                            }
                            body = sb.toString();
                        } finally {
                            instream.close();
                            EntityUtils.consume(entity);
                        }
                    }
                } catch (ParseException | IOException e) {
                    e.printStackTrace();
                }
                handler.completed(body);
                close(client);
            }

            @Override
            public void cancelled() {
                handler.cancelled();
                close(client);
            }
        });
    }

    public static void send(String url, Object param){
        // 异步调用成功后处理
        AsynHttpCallBack handler = (respBody) -> {
            return (HashMap<String, Object>) JsonMapper.nonEmptyMapper().fromJson(respBody, HashMap.class);
        };

        try {
            // 异步调用平台充电预约接口
            send(url, param, "utf-8", handler);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭client对象
     *
     * @param client client
     */
    private static void close(CloseableHttpAsyncClient client) {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
