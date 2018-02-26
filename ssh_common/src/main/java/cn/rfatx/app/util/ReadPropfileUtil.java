package cn.rfatx.app.util;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Properties;

/**
 * 读取档案的工具类
 */
public class ReadPropfileUtil {
    private static ReadPropfileUtil install = null;
    public static Properties prop = null;


    static {
        try {
            prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("clientInfo.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ReadPropfileUtil getInstall() {
        if (null == install) {
            install = new ReadPropfileUtil();
        }
        return install;
    }
}
