package cn.rfatx.app.util.installation;


import cn.rfatx.app.util.installation.support.res.AXmlResourceParser;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 对Apk文件检测
 */
public class AnalysisApk {
    private static final String APKMAINFILE = "AndroidManifest.xml";

    /**
     * 对Apk资源路径及文件大小进行解析
     * @param apkUrl apk地址
     * @param logoUrl 地址图标
     * @return apk信息
     */
    public static String[] unZip(String apkUrl, String logoUrl) {
        String[] st = new String[2];
        byte[] b = new byte['Ѐ'];
        OutputStream outputStream = null;
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(new File(apkUrl));
            Enumeration enumeration = zipFile.entries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements()) {
                zipEntry = (ZipEntry) enumeration.nextElement();
                if (!zipEntry.isDirectory()) {
                    if ("AndroidManifest.xml".equalsIgnoreCase(zipEntry.getName())) {
                        try {
                            AXmlResourceParser parser = new AXmlResourceParser();
                            parser.open(zipFile.getInputStream(zipEntry));
                            for (; ; ) {
                                int type = parser.next();
                                if (type == 1) {
                                    break;
                                }
                                switch (type) {
                                    case 2:
                                        for (int i = 0; i != parser.getAttributeCount(); i++) {
                                            if ("versionName".equalsIgnoreCase(parser.getAttributeName(i))) {
                                                st[0] = getAttributeValue(parser, i);
                                            } else if ("package".equalsIgnoreCase(parser.getAttributeName(i))) {
                                                st[1] = getAttributeValue(parser, i);
                                            }
                                        }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if ((logoUrl != null) && (!"".equals(logoUrl)) &&
                            ("res/drawable-ldpi/icon.png".equals(zipEntry.getName()))) {
                        outputStream = new FileOutputStream(logoUrl);
                        InputStream inputStream = zipFile.getInputStream(zipEntry);
                        int length;
                        while ((length = inputStream.read(b)) > 0) {
                            outputStream.write(b, 0, length);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return st;
    }

    /**
     * 获取值并对其解析
     * @param parser 解析器
     * @param index 索引
     * @return 安卓信息
     */
    private static String getAttributeValue(AXmlResourceParser parser, int index) {
        int type = parser.getAttributeValueType(index);
        int data = parser.getAttributeValueData(index);
        if (type == 3) {
            return parser.getAttributeValue(index);
        }
        if (type == 2) {
            return String.format("?%s%08X", new Object[]{getPackage(data), Integer.valueOf(data)});
        }
        if (type == 1) {
            return String.format("@%s%08X", new Object[]{getPackage(data), Integer.valueOf(data)});
        }
        if (type == 4) {
            return String.valueOf(Float.intBitsToFloat(data));
        }
        if (type == 17) {
            return String.format("0x%08X", new Object[]{Integer.valueOf(data)});
        }
        if (type == 18) {
            return data != 0 ? "true" : "false";
        }
        if (type == 5) {
            return Float.toString(complexToFloat(data)) + DIMENSION_UNITS[(data & 0xF)];
        }
        if (type == 6) {
            return Float.toString(complexToFloat(data)) + FRACTION_UNITS[(data & 0xF)];
        }
        if ((type >= 28) && (type <= 31)) {
            return String.format("#%08X", new Object[]{Integer.valueOf(data)});
        }
        if ((type >= 16) && (type <= 31)) {
            return String.valueOf(data);
        }
        return String.format("<0x%X, type 0x%02X>", new Object[]{Integer.valueOf(data), Integer.valueOf(type)});
    }

    private static String getPackage(int id) {
        if (id >>> 24 == 1) {
            return "android:";
        }
        return "";
    }


    public static float complexToFloat(int complex) {
        return (complex & 0xFF00) * RADIX_MULTS[(complex >> 4 & 0x3)];
    }

    private static final float[] RADIX_MULTS = {0.00390625F, 3.051758E-5F, 1.192093E-7F, 4.656613E-10F};
    private static final String[] DIMENSION_UNITS = {"px", "dip", "sp", "pt", "in", "mm", "", ""};
    private static final String[] FRACTION_UNITS = {"%", "%p", "", "", "", "", "", ""};
}

