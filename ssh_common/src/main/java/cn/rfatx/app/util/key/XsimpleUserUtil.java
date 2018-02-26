package cn.rfatx.app.util.key;


import cn.rfatx.core.diva.security.utils.Digests;
import cn.rfatx.core.diva.utils.Encodes;
import com.google.common.base.Strings;

/**
 * 用户权限加密
 */
public class XsimpleUserUtil {
    private static volatile XsimpleUserUtil util;

    public static XsimpleUserUtil getInstance() {
        synchronized (XsimpleUserUtil.class) {
            if (util == null) {
                util = new XsimpleUserUtil();
            }
            return util;
        }
    }

    /**
     * 密码加密
     * @param password 密码
     * @param salt 数据
     * @return 加密信息
     */
    public String encodeHexPassword(String password, String salt) {
        byte[] hashPassword = Digests.sha1(password.getBytes(), Encodes.decodeHex(salt), 1024);
        return Encodes.encodeHex(hashPassword);
    }


    public UserPasswordKey entryptPassword(String password) {
        return entryptPassword(password, false);
    }

    /**
     * 密码解密
     * @param password 密码
     * @param flag 判断参数
     * @return 密码信息
     */
    public UserPasswordKey entryptPassword(String password, boolean flag) {
        UserPasswordKey map = new UserPasswordKey();
        if (flag) {
            password = Strings.isNullOrEmpty(password) ? "123456" : password;
        } else {
            password = Strings.isNullOrEmpty(password) ? "" : password;
        }
        byte[] salt = Digests.generateSalt(8);
        byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);
        password = Encodes.encodeHex(hashPassword);
        map.setPassword(password);
        map.setSalt(Encodes.encodeHex(salt));
        return map;
    }

    /**
     * 用户密钥
     */
    public class UserPasswordKey {
        private String password;

        public UserPasswordKey() {
        }

        public String getPassword() {
            return this.password;
        }

        private String salt;

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return this.salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }
    }
}
