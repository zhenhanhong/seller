package cn.rfatx.listener;

import cn.rfatx.app.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import util.FtpClientUtil;

/**
 * ftp服务器文件下载
 * Created by liuqingdi on 2017/03/31.
 */
@Component
public class ResourceInitListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger log = LoggerFactory.getLogger(ResourceInitListener.class);

    @Value("#{commonInfo['ftpServer']}")
    private String ftpServer;

    @Value("#{commonInfo['ftpPort']}")
    private int ftpPort;

    @Value("#{commonInfo['ftpUser']}")
    private String ftpUser;

    @Value("#{commonInfo['ftpPassword']}")
    private String ftpPassword;

    @Value("#{commonInfo['ftpRootPath']}")
    private String ftpRootPath;

    @Value("#{commonInfo['uploadBasePath']}")
    private String uploadBasePath;

    /**
     * 文件下载
     *
     * @param event ContextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (null == event.getApplicationContext().getParent()) {

            try {
                String path = PathUtil.getRootPath();
                if (StringUtils.isEmpty(path)) {
                    log.error("根目录取得失败。");
                } else {
                    FtpClientUtil ftp = new FtpClientUtil(ftpServer, ftpPort, ftpUser, ftpPassword);
                    if (ftp.ftpLogin()) {
                        if (ftp.downLoadDirectory(path + "/", ftpRootPath + uploadBasePath + "/uf")) {
                            log.debug("ftp文件（uf）下载成功。");
                        } else {
                            log.error("ftp文件（uf）下载失败。");
                        }
                        if (ftp.downLoadDirectory(path + "/WEB-INF/views/", ftpRootPath + uploadBasePath + "/jd")) {
                            log.debug("ftp文件（jd）下载成功。");
                        } else {
                            log.error("ftp文件（jd）下载失败。");
                        }
                        ftp.ftpLogOut();
                    } else {
                        log.error("ftp登录失败。");
                    }
                }
            } catch (Exception e) {
                log.error("ftp文件下载失败。", e);
            }
        }
    }

}
