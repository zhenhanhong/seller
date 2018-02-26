//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.rfatx.app.util.key;

import cn.rfatx.app.util.ReadPropfileUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 高级加密标准
 */
public class AES {
    private static final String aesKey;
    private static final String ivParameter;

    public AES() {
    }

    /**
     * 解密
     * @param sSrc 解析
     * @param sKey 密码
     * @return 密码
     * @throws Exception 异常
     */
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            if(sKey == null || "".equals(sKey)) {
                sKey = aesKey;
            }

            if(sKey == null) {
                return null;
            } else if(sKey.length() != 16) {
                return null;
            } else {
                try {
                    byte[] ex = sKey.getBytes("ASCII");
                    SecretKeySpec skeySpec = new SecretKeySpec(ex, "AES");
                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
                    cipher.init(2, skeySpec, iv);
                    byte[] encrypted1 = hex2byte(sSrc);
                    byte[] original = cipher.doFinal(encrypted1);
                    return new String(original, "utf-8");
                } catch (Exception var8) {
                    return null;
                }
            }
        } catch (Exception var9) {
            return null;
        }
    }

    /**
     * 加密
     * @param sSrc 解析
     * @param sKey 密码
     * @return 密码
     * @throws Exception 异常
     */
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if(sKey == null || "".equals(sKey)) {
            sKey = aesKey;
        }

        if(sKey == null) {
            return null;
        } else if(sKey.length() != 16) {
            return null;
        } else {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(1, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return byte2hex(encrypted).toLowerCase();
        }
    }

    /**
     * 二进制转换
     * @param strhex 数据
     * @return 转换结果
     */
    public static byte[] hex2byte(String strhex) {
        if(strhex == null) {
            return null;
        } else {
            int l = strhex.length();
            if(l % 2 == 1) {
                return null;
            } else {
                byte[] b = new byte[l / 2];

                for(int i = 0; i != l / 2; ++i) {
                    b[i] = (byte)Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
                }

                return b;
            }
        }
    }

    /**
     * 二进制转换
     * @param b 数据
     * @return 数据结果
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";

        for(int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if(stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }

        return hs.toUpperCase();
    }

    static {
        ReadPropfileUtil.getInstall();
        aesKey = ReadPropfileUtil.prop.getProperty("aesKey");
        ReadPropfileUtil.getInstall();
        ivParameter = ReadPropfileUtil.prop.getProperty("aesKey");
    }
}
