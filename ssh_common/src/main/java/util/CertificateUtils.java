package util;

import org.springframework.util.Base64Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * <p> 数字签名/加密解密工具包 </p>
 *
 */
public class CertificateUtils {

    /**
     * Java密钥库(Java 密钥库，JKS)KEY_STORE
     */
    public static final String KEY_STORE = "JKS";

    public static final String X509 = "X.509";

    /**
     * <p> 获得密钥库 </p>
     *
     * @param keyStorePath 密钥库存储路径
     * @param password 密钥库密码
     * @return 密钥库
     * @throws Exception 异常
     */
    private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
        FileInputStream in = null;
        try {
            in = new FileInputStream(keyStorePath);
            KeyStore keyStore = KeyStore.getInstance(KEY_STORE);
            keyStore.load(in, password.toCharArray());
            return keyStore;
        }finally {
            if(in != null){
                try{
                    in.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
    }

    /**
     * <p> 获得证书 </p>
     *
     * @param certificatePath 证书存储路径
     * @return 证书
     * @throws Exception 异常
     */
    private static Certificate getCertificate(String certificatePath) throws Exception {
        CertificateFactory certificateFactory = CertificateFactory.getInstance(X509);
        FileInputStream in = null;
        try {
            in = new FileInputStream(certificatePath);
            Certificate certificate = certificateFactory.generateCertificate(in);
            return certificate;
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    /**
     * <p> 根据密钥库获得证书 </p>
     *
     * @param keyStorePath 密钥库存储路径
     * @param alias 密钥库别名
     * @param password 密钥库密码
     * @return 证书
     * @throws Exception 异常
     */
    private static Certificate getCertificate(String keyStorePath, String alias, String password) throws Exception {
        KeyStore keyStore = getKeyStore(keyStorePath, password);
        Certificate certificate = keyStore.getCertificate(alias);
        return certificate;
    }

    /**
     * <p> 生成数据签名 </p>
     *
     * @param data 源数据
     * @param keyStorePath 密钥库存储路径
     * @param alias 密钥库别名
     * @param password 密钥库密码
     * @return 签名
     * @throws Exception 异常
     */
    public static byte[] sign(byte[] data, String keyStorePath, String alias, String password) throws Exception {
        // 获得证书
        X509Certificate x509Certificate = (X509Certificate) getCertificate(keyStorePath, alias, password);
        // 获取私钥
        KeyStore keyStore = getKeyStore(keyStorePath, password);
        // 取得私钥
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
        // 构建签名
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * <p> 生成数据签名并以BASE64编码 </p>
     *
     * @param data 源数据
     * @param keyStorePath 密钥库存储路径
     * @param alias 密钥库别名
     * @param password 密钥库密码
     * @return BASE64编码
     * @throws Exception 异常
     */
    public static String signToBase64(byte[] data, String keyStorePath, String alias, String password) throws Exception {
        return new String(Base64Utils.encode(sign(data, keyStorePath, alias, password)));
    }

    /**
     * <p> 验证签名 </p>
     *
     * @param data 已加密数据
     * @param sign 数据签名[BASE64]
     * @param certificatePath 证书存储路径
     * @return 签名
     * @throws Exception 异常
     */
    public static boolean verifySign(byte[] data, String sign, String certificatePath) throws Exception {
        // 获得证书
        X509Certificate x509Certificate = (X509Certificate) getCertificate(certificatePath);
        // 获得公钥
        PublicKey publicKey = x509Certificate.getPublicKey();
        // 构建签名
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initVerify(publicKey);
        signature.update(data);

        return signature.verify(Base64Utils.decode(sign.getBytes("UTF-8")));
    }

    /**
     * <p> BASE64解码 签名校验 </p>
     *
     * @param base64String BASE64编码字符串
     * @param sign 数据签名[BASE64]
     * @param certificatePath 证书存储路径
     * @return 签名校验
     * @throws Exception 异常
     */
    public static boolean verifyBase64Sign(String base64String, String sign, String certificatePath) throws Exception {
        byte[] data = Base64Utils.decode(base64String.getBytes("UTF-8"));
        return verifySign(data, sign, certificatePath);
    }
}