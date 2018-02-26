package cn.rfatx.app.util.installation;


import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;
import com.google.common.base.Strings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 读取Ipa和Android应用程序的工具类
 */
public class IpaAndroidReaderUtil {
    private static final String METADATA_FILE_NAME = "info.plist";
    private static volatile IpaAndroidReaderUtil ipaAndroidReaderUtil = null;

    public static IpaAndroidReaderUtil getInstall() {
        synchronized (IpaAndroidReaderUtil.class) {
            if (ipaAndroidReaderUtil == null) {
                ipaAndroidReaderUtil = new IpaAndroidReaderUtil();
            }
            return ipaAndroidReaderUtil;
        }
    }

    /**
     * 获得api元数据
     *
     * @param ipa 文件数据
     * @return 数据
     * @throws IOException 获得失败
     */
    public ApkPlistMetadata getMetadata(File ipa)
            throws IOException {
        ApkPlistMetadata metadata = null;
        ZipFile zip = null;
        try {
            zip = new ZipFile(ipa);
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String entryName = entry.getName();
                if (entryName.toLowerCase().indexOf("info.plist") != -1) {
                    metadata = readPlist(zip.getInputStream(entry));
                    if (metadata != null) {
                        if (!Strings.isNullOrEmpty(metadata.getVersionNum())) {
                            break;
                        }
                    }
                }
            }
        } finally {
            if (zip != null) {
                try {
                    zip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return metadata;
    }

    /**
     * 读取apk参数
     *
     * @param plist 参数
     * @return 参数数据
     */
    private ApkPlistMetadata readPlist(InputStream plist) {
        ApkPlistMetadata metadata = new ApkPlistMetadata();
        try {
            NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(plist);
            metadata.setPackageName(rootDict.objectForKey("CFBundleIdentifier") == null ? "" : rootDict.objectForKey("CFBundleIdentifier").toString());
            metadata.setDiscription(rootDict.objectForKey("CFBundleDisplayName") == null ? "" : rootDict.objectForKey("CFBundleDisplayName").toString());
            metadata.setVersionNum(rootDict.objectForKey("CFBundleVersion") == null ? "" : rootDict.objectForKey("CFBundleVersion").toString());
        } catch (Exception e) {
            metadata = null;
        }
        return metadata;
    }

    /**
     * 获取安卓数据
     *
     * @param android 安卓
     * @return 数据
     * @throws IOException 获取异常
     */
    public ApkPlistMetadata getAndroidData(File android)
            throws IOException {
        ApkPlistMetadata metadata = new ApkPlistMetadata();
        String[] str = AnalysisApk.unZip(android.toString(), null);
        metadata.setVersionNum(str[0]);
        metadata.setPackageName(str[1]);
        return metadata;
    }
}

