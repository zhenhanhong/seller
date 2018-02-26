package util;

import com.ning.http.util.DateUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Date;
import java.util.TimeZone;

public class FtpClientUtil {
    private FTPClient ftpClient;
    private String strIp;
    private int intPort;
    private String user;
    private String password;

    private static Logger logger = LoggerFactory.getLogger(FtpClientUtil.class.getName());

    /* *
     * Ftp构造函数 
     */
    public FtpClientUtil(String strIp, int intPort, String user, String Password) {
        this.strIp = strIp;
        this.intPort = intPort;
        this.user = user;
        this.password = Password;
        this.ftpClient = new FTPClient();
    }

    /**
     * 登录FTP服务器
     *
     * @return 登录结果
     */
    public boolean ftpLogin() {
        boolean isLogin = false;
        FTPClientConfig ftpClientConfig = new FTPClientConfig();
        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());
        this.ftpClient.setControlEncoding("UTF-8");
        this.ftpClient.configure(ftpClientConfig);
        try {
            if (this.intPort > 0) {
                this.ftpClient.connect(this.strIp, this.intPort);
            } else {
                this.ftpClient.connect(this.strIp);
            }
            // FTP服务器连接回答  
            int reply = this.ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                this.ftpClient.disconnect();
                logger.error("登录FTP服务失败！");
                return isLogin;
            }
            this.ftpClient.login(this.user, this.password);
            // 设置传输协议  
            this.ftpClient.enterLocalPassiveMode();
            this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            logger.info(this.user + "成功登陆FTP服务器");
            isLogin = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(this.user + "登录FTP服务失败！" + e.getMessage());
        }
        this.ftpClient.setBufferSize(1024 * 2);
        this.ftpClient.setDataTimeout(30 * 1000);
        return isLogin;
    }

    /**
     * 退出并关闭连接。
     */
    public void ftpLogOut() {
        if (null != this.ftpClient && this.ftpClient.isConnected()) {
            try {
                boolean reuslt = this.ftpClient.logout();// 退出FTP服务器  
                if (reuslt) {
                    logger.info("成功退出服务器");
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.warn("退出FTP服务器异常！" + e.getMessage());
            } finally {
                try {
                    this.ftpClient.disconnect();// 关闭FTP服务器的连接  
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.warn("关闭FTP服务器的连接异常！");
                }
            }
        }
    }

    /***
     * 上传Ftp文件
     *
     * @param localFile        当地文件
     * @param romotUpLoadePath 上传服务器路径 - 应该以/结束
     * @return 上传结果
     */
    public boolean uploadFile(File localFile, String romotUpLoadePath) {
        BufferedInputStream inStream = null;
        boolean success = false;
        try {
            this.ftpClient.enterLocalPassiveMode();
            this.ftpClient.changeWorkingDirectory(romotUpLoadePath);// 改变工作路径  
            inStream = new BufferedInputStream(new FileInputStream(localFile));
            logger.info(localFile.getName() + "开始上传.....");
            success = this.ftpClient.storeFile(localFile.getName(), inStream);
            if (success == true) {
                logger.info(localFile.getName() + "上传成功");
                return success;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(localFile + "未找到");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    /***
     * 下载文件
     *
     * @param remoteFileName     待下载文件名称
     * @param localDires         下载到当地那个路径下
     * @param remoteDownLoadPath remoteFileName所在的路径
     * @return 下载结果
     */

    public boolean downloadFile(String remoteFileName, String localDires,
                                String remoteDownLoadPath) {
        // 下载文件路径做成
        File localPath = new File(localDires);
        if (!localPath.exists()) {
            localPath.mkdir();
        }

        String strFilePath = localDires + remoteFileName;
        BufferedOutputStream outStream = null;
        boolean success = false;
        try {
            this.ftpClient.enterLocalPassiveMode();
            this.ftpClient.changeWorkingDirectory(remoteDownLoadPath);
            outStream = new BufferedOutputStream(new FileOutputStream(
                    strFilePath));
            logger.info(remoteFileName + "开始下载....");
            success = this.ftpClient.retrieveFile(remoteFileName, outStream);
            if (success == true) {
                logger.info(remoteFileName + "成功下载到" + strFilePath);
                return success;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            logger.error(remoteFileName + "下载失败");
        } finally {
            if (null != outStream) {
                try {
                    outStream.flush();
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (success == false) {
            logger.error(remoteFileName + "下载失败!!!");
        }
        return success;
    }

    /***
     * 上传文件夹
     *
     * @param localDirectory      当地文件夹
     * @param remoteDirectoryPath Ftp 服务器路径 以目录"/"结束
     * @return 结果
     */
    public boolean uploadDirectory(String localDirectory,
                                   String remoteDirectoryPath) {
        File src = new File(localDirectory);
        try {
            this.ftpClient.enterLocalPassiveMode();
            remoteDirectoryPath = remoteDirectoryPath + src.getName() + "/";
            this.ftpClient.makeDirectory(remoteDirectoryPath);
            // ftpClient.listDirectories();  
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(remoteDirectoryPath + "目录创建失败");
        }
        File[] allFile = src.listFiles();
        for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
            if (!allFile[currentFile].isDirectory()) {
                String srcName = allFile[currentFile].getPath().toString();
                uploadFile(new File(srcName), remoteDirectoryPath);
            }
        }
        for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
            if (allFile[currentFile].isDirectory()) {
                // 递归  
                uploadDirectory(allFile[currentFile].getPath().toString(),
                        remoteDirectoryPath);
            }
        }
        return true;
    }

    /***
     * 下载文件夹
     *
     * @param localDirectoryPath 本地地址
     * @param remoteDirectory    远程文件夹
     * @return 结果
     */
    public boolean downLoadDirectory(String localDirectoryPath, String remoteDirectory) {
        try {
            String fileName = new File(remoteDirectory).getName();
            localDirectoryPath = localDirectoryPath + fileName + "//";
            new File(localDirectoryPath).mkdirs();
            FTPFile[] allFile = this.ftpClient.listFiles(remoteDirectory);
            for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
                if (!allFile[currentFile].isDirectory()) {
                    downloadFile(allFile[currentFile].getName(), localDirectoryPath, remoteDirectory);
                }
            }
            for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
                if (allFile[currentFile].isDirectory()) {
                    String strremoteDirectoryPath = remoteDirectory + "/" + allFile[currentFile].getName();
                    downLoadDirectory(localDirectoryPath, strremoteDirectoryPath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("下载文件夹失败");
            return false;
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param remotePath 远程文件地址
     * @param srcFname   文件名称
     * @return 结果
     */
    public boolean removeFile(String remotePath, String srcFname) {
        boolean result = false;
        try {
            this.ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(remotePath);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            // 循环遍历
            for (FTPFile ftpFile : ftpFiles) {
                // 找到需要下载的文件
                if (ftpFile.getName().equals(srcFname)) {
                    System.out.println("delete...");
                    // 创建本地目录
                    // 删除前时间
                    Date startTime = new Date();
                    if (ftpFile.isDirectory()) {
                        // 删除多个文件
                        result = ftpClient.removeDirectory(srcFname);
                    } else {
                        // 删除单个文件
                        result = ftpClient.deleteFile(srcFname);
                    }
                    // 删除完时间
                    Date endTime = new Date();
                    logger.info("删除完成时间：" + DateUtil.formatDate(endTime, "YYYY/MM/dd HH:mm:ss"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("删除文件失败:" + remotePath + srcFname);
            result = false;
        }
        return result;
    }

    /***
     * 创建文件夹
     *
     * @param directoryName       文件夹名
     * @param remoteDirectoryPath Ftp 服务器路径 以目录"/"结束
     * @return 结果
     */
    public boolean createDirectory(String directoryName,
                                   String remoteDirectoryPath) {
        try {
            remoteDirectoryPath = remoteDirectoryPath + directoryName + "/";
            this.ftpClient.enterLocalPassiveMode();
            this.ftpClient.makeDirectory(remoteDirectoryPath);
            logger.info(remoteDirectoryPath + "目录创建成功");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(remoteDirectoryPath + "目录创建失败");
            return false;
        }
        return true;
    }

    /***
     * 创建文件夹
     *
     * @param directoryName       文件夹名
     * @param remoteDirectoryPath Ftp 服务器路径 以目录"/"结束
     * @return 结果
     */
    public boolean createDirectories(String directoryName, String remoteDirectoryPath) {
        try {
            if (StringUtils.isEmpty(directoryName)) {
                logger.info("创建目录不能为空");
                return false;
            }
            String[] strDir = directoryName.split("/");
            StringBuffer sbDir = new StringBuffer();
            sbDir.append(remoteDirectoryPath);
            this.ftpClient.enterLocalPassiveMode();
            for (String dir : strDir) {
                if (!StringUtils.isEmpty(dir)) {
                    sbDir.append(dir);
                    sbDir.append("/");
                    this.ftpClient.makeDirectory(sbDir.toString());
                }
            }
            logger.info(remoteDirectoryPath + "目录创建成功");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(remoteDirectoryPath + "目录创建失败");
            return false;
        }
        return true;
    }

    // FtpClient的Set 和 Get 函数  
    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}