package util;

import cn.rfatx.core.diva.utils.Encodes;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * 签名算法
 */
public class RsaUtils {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /*
     加密
     */
    public static final String KET_SIGNATUREINFO= "signatureInfo";


    /**
     * 根据私钥实施加密
     *
     * @param signedData 加密对象
     * @param privateKey 私钥
     * @return base64化的签名后字符串
     * @throws Exception 异常
     */
    public static String sign(byte[] signedData, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(signedData);
        byte[] b = signature.sign();
        String strBase = Encodes.encodeBase64(b);
        return strBase;
    }


    /**
     * 根据公钥实施验证
     *
     * @param base            根据公钥实施验证
     * @param base64Signature base64化的签名后字符串
     * @param publicKey       公钥
     * @return true: OK, false : NG
     * @throws Exception 异常
     */
    public static boolean verifySignature(byte[] base, String base64Signature, PublicKey publicKey)
            throws Exception {
        Signature sig;
        sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initVerify(publicKey);
        sig.update(base);
        byte[] signature = Encodes.decodeBase64(base64Signature);
        if (sig.verify(signature)) {
            return true;
        }
        return false;
    }

    /**
     * 从证书文件取得公钥
     *
     * @param certFile 公钥和私钥KeyPair
     * @return 公钥
     * @throws Exception 异常
     */
    public static PublicKey getPublicKey(String certFile) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream in = null;
        try {
            in = new FileInputStream(certFile);
            while (in.available() > 0) {
                Certificate cert = cf.generateCertificate(in);
                X509Certificate x509Certificate = (X509Certificate) cert;
                return x509Certificate.getPublicKey();
            }
        } finally {
            if (in != null) {
                in.close();
            }

        }
        return null;
    }

    /**
     * 从keyPair取得私钥
     *
     * @param keyPair 公钥和私钥KeyPair
     * @return 私钥
     * @throws Exception 异常
     */
    public static PrivateKey getPrivateKey(KeyPair keyPair) throws Exception {
        PrivateKey privateKey = keyPair.getPrivate();
        return privateKey;
    }

    /**
     * 签名后向平台发送Post请求
     *
     * @param alias        别名
     * @param password     密码
     * @param keyStoreType keyStore类型
     * @param keyStoreFile keyStore文件
     * @return 公钥和私钥KeyPair
     * @throws Exception 异常
     */
    public static KeyPair getKeyPair(String alias, String password, String keyStoreType, String keyStoreFile) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(keyStoreFile);
            keyStore.load(inputStream, password.toCharArray());
            Key key = keyStore.getKey(alias, password.toCharArray());
            KeyPair keyPair = null;
            if (key instanceof PrivateKey) {
                Certificate certificate = keyStore.getCertificate(alias);
                PublicKey publicKey = certificate.getPublicKey();
                keyPair = new KeyPair(publicKey, (PrivateKey) key);
            }
            return keyPair;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }


    /**
     * 根据公钥实施验证
     *
     * @param certFile 证书
     * @param base 未签名原始数据
     * @param paraMap 全体数据
     * @return true: OK, false : NG
     * @throws Exception 异常
     */
    public static boolean verify(String certFile, byte[] base, Map paraMap ) throws Exception {
        PublicKey publicKey = RsaUtils.getPublicKey(certFile);
        String signatureInfo = (String) paraMap.get(KET_SIGNATUREINFO);
        boolean ret = verifySignature(base, signatureInfo, publicKey);
        return ret;
    }

}
