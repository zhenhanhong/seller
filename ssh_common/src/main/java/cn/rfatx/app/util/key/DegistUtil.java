package cn.rfatx.app.util.key;


import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对密码等机密数据进行加密处理后再存入数据库
 */
public class DegistUtil {
    public static String produceDegistCode(String seq) {
        if (seq == null) {
            return null;
        }

        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] buf = md.digest(seq.getBytes());

            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}

