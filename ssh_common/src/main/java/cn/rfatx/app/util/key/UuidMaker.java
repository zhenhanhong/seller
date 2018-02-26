package cn.rfatx.app.util.key;

import java.util.UUID;

/**
 * 制造商识别码
 */
public class UuidMaker {
    private static UuidMaker maker;

    public static UuidMaker getInstance() {
        if (maker == null) {
            synchronized (UuidMaker.class) {
                maker = new UuidMaker();
            }
        }
        return maker;
    }

    /**
     * 获取识别码
     * @param flag 判断参数
     * @return 获取成功失败
     */
    public String getUuid(boolean flag) {
        String uuid = UUID.randomUUID().toString();
        if (flag) {
            uuid = uuid.replaceAll("-", "");
        }
        return uuid;
    }
}

