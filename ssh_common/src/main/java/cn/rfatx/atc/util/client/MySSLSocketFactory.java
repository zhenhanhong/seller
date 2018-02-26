package cn.rfatx.atc.util.client;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * 自己加密的端口类
 */
public class MySSLSocketFactory extends SSLSocketFactory {

    static {
        mySSLSocketFactory = new MySSLSocketFactory(createSContext());
    }

    private static MySSLSocketFactory mySSLSocketFactory = null;


    /**
     * 创建加密环境
     *
     * @return 加密信息
     */
    private static SSLContext createSContext() {
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            if(sslcontext != null) {
                sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, null);
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
            return null;
        }
        return sslcontext;
    }

    @SuppressWarnings("deprecation")
    private MySSLSocketFactory(SSLContext sslContext) {
        super(sslContext);
        this.setHostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    public static MySSLSocketFactory getInstance() {
        if (mySSLSocketFactory != null) {
            return mySSLSocketFactory;
        } else {
            return mySSLSocketFactory = new MySSLSocketFactory(createSContext());
        }
    }

}