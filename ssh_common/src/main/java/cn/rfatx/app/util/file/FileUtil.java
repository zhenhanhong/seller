//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.rfatx.app.util.file;

import cn.rfatx.app.util.PathUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * File处理
 */
public class FileUtil {
    private static volatile FileUtil fileUtil;

    private FileUtil() {
    }

    public static FileUtil getInstance() {
        synchronized (FileUtil.class) {
            if (fileUtil == null) {
                fileUtil = new FileUtil();
            }
            return fileUtil;
        }
    }

    /**
     * 保存到桌面
     * @param imageInf 图片情报
     * @param path 路径
     * @return 保存成功失败
     * @throws IOException 保存异常
     */
    public static boolean saveFileToDisk(String imageInf, Object path) throws IOException {
        if(imageInf == null) {
            return false;
        } else {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(imageInf);

            for(int out = 0; out < b.length; ++out) {
                if(b[out] < 0) {
                    b[out] = (byte)(b[out] + 256);
                }
            }

            FileOutputStream var5 = null;
            FileOutputStream var6 = null;
            try {
                if (path instanceof String) {
                    var6 = new FileOutputStream((String) path);
                }

                if (path instanceof File) {
                    var5 = new FileOutputStream((File) path);
                    var5.write(b);
                    var5.flush();
//                    var5.close();
                    return true;
                } else {
                    return false;
                }
            }finally {
                try {
                    if (var5 != null) {
                        var5.close();
                    }
                    if (var6 != null) {
                        var6.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除单个文件夹
     * @param fileAddress 文件地址
     */
    public void deleteFile(String fileAddress) {
        File file = new File(fileAddress);
        if(file.isDirectory()) {
            this.delFolder(fileAddress);
        } else if(file.exists()) {
            file.delete();
        }

    }

    /**
     * 删除文件夹综合操作
     * @param folderPath 文件路径
     */
    public void delFolder(String folderPath) {
        try {
            this.delAllFile(folderPath);
            String e = folderPath.toString();
            File myFilePath = new File(e);
            myFilePath.delete();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    /**
     * 删除单个空文件夹
     * @param list 文件集合
     */
    public void deleteNullFile(List<File> list) {
        if(list != null) {
            for(int i = 0; i < list.size(); ++i) {
                File temp = (File)list.get(i);
                if(temp.isDirectory() && temp.listFiles().length <= 0) {
                    temp.delete();
                }
            }
        }

    }

    /**
     * 删除空文件夹
     * @param path 路径
     */
    public void deleteNullFileDirectory(String path) {
        File file = new File(path);
        if(file.exists()) {
            if(file.isDirectory()) {
                String[] tempList = file.list();
                File temp = null;

                for(int i = 0; i < tempList.length; ++i) {
                    if(path.endsWith(File.separator)) {
                        temp = new File(path + tempList[i]);
                    } else {
                        temp = new File(path + File.separator + tempList[i]);
                    }

                    if(temp.isDirectory()) {
                        if(temp.listFiles().length <= 0) {
                            temp.delete();
                        } else {
                            this.deleteNullFileDirectory(path + "/" + tempList[i]);
                        }
                    }
                }

            }
        }
    }

    /**
     * 删除指定文件夹下的所有文件夹
     * @param path 路径
     * @return 删除成功失败
     */
    public boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if(!file.exists()) {
            return flag;
        } else if(!file.isDirectory()) {
            return flag;
        } else {
            String[] tempList = file.list();
            File temp = null;

            for(int i = 0; i < tempList.length; ++i) {
                if(path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }

                if(temp.isFile()) {
                    temp.delete();
                }

                if(temp.isDirectory()) {
                    this.delAllFile(path + "/" + tempList[i]);
                    this.delFolder(path + "/" + tempList[i]);
                    flag = true;
                }
            }

            return flag;
        }
    }

    /**
     * 读取某个目录下所有的文件的全路径和文件名的集合
     * @param path 路径
     * @return 文件集合
     */
    public List<File> getAllFile(String path) {
        ArrayList fileList = new ArrayList();
        File file = new File(path);
        if(!file.exists()) {
            return null;
        } else if(!file.isDirectory()) {
            return null;
        } else {
            String[] tempList = file.list();
            File temp = null;

            for(int i = 0; i < tempList.length; ++i) {
                if(path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }

                if(temp.isFile()) {
                    fileList.add(temp);
                }

                if(temp.isDirectory()) {
                    List f = this.getAllFile(path + "/" + tempList[i]);
                    if(f != null) {
                        fileList.addAll(f);
                    }
                }
            }

            return fileList;
        }
    }

    /**
     * 复制文件到tomcat
     * @param uploadFile 上传下载文件
     * @param targetDirectory 目标地址
     * @param uploadFileFileName 上传下载文件名
     * @param currtTime 时间
     * @param tip 版本号
     * @param version 版本信息
     * @throws IOException 导入异常
     */
    public void copyFileToTomcat(File uploadFile, String targetDirectory, String uploadFileFileName, String currtTime, String tip, String version) throws IOException {
        String realFileName = "";
        if(tip.equals("2")) {
            realFileName = currtTime + uploadFileFileName.substring(uploadFileFileName.lastIndexOf("."));
        } else {
            realFileName = currtTime + "_" + uploadFileFileName;
        }

        File target = new File(targetDirectory, realFileName);
        FileUtils.copyFile(uploadFile, target);
    }

    /**
     * 保存文件
     * @param savedir 保存
     * @param address 路径
     * @param uploadFile 上传下载文件
     * @return 保存是否成功
     */
    public boolean saveFile(File savedir, String address, File uploadFile) {
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        File saveFile = new File(savedir, address);
        File writeFile = new File(saveFile + "/");
        boolean b = false;
        FileOutputStream fos = null;

        try {
            FileInputStream e = new FileInputStream(uploadFile);
            bis = new BufferedInputStream(e);
            fos = new FileOutputStream(writeFile);
            bos = new BufferedOutputStream(fos);
            byte[] buf = new byte[2097152];
            boolean len = true;

            int len1;
            while((len1 = bis.read(buf)) != -1) {
                bos.write(buf, 0, len1);
            }

            b = true;
        } catch (Exception var21) {
            var21.printStackTrace();
        } finally {
            try {
                if(null != bis) {
                    bis.close();
                }

                if(null != bos) {
                    bos.close();
                }

                if(fos != null){
                    fos.close();
                }

                if(!b && writeFile.exists()) {
                    writeFile.delete();
                }
            } catch (IOException var20) {
                var20.printStackTrace();
            }

        }

        return b;
    }

    /**
     * 获得文件夹的大小
     * @param path 路径
     * @return 文件大小
     * @throws IOException 获取异常
     */
    public String getFileSize(String path) throws IOException {
        String size = "0";
        FileInputStream fis = null;
        try {
            File e = new File(path);
            fis = new FileInputStream(e);
            size = fis.available() + "";
        } catch (Exception var5) {
            ;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return size;
    }

    /**
     * 读取日期
     * @param fileName 文件名
     * @return 日期
     */
    public String readDate(File fileName) {
        StringBuffer sb = new StringBuffer();
        FileReader e = null;
        try {
            e = new FileReader(fileName);
            char[] ch = new char[1024];

            for(int d = e.read(ch); d != -1; d = e.read(ch)) {
                String str = new String(ch, 0, d);
                sb.append(str);
            }
        } catch (FileNotFoundException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }finally {
            if (e != null) {
                try {
                    e.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    /**
     * 返回空文件夹
     * @param path 路径
     * @return 空文件
     */
    public File existFile(String path) {
        File file = new File(PathUtil.getRootPath() + File.separatorChar + path);
        return file.exists()?file:null;
    }

    /**
     * 保存文件路径
     * @param url 地址
     * @param saveAddress 保存路径
     * @return 保存成功失败
     */
    public boolean saveFileByUrl(String url, String saveAddress) {
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        boolean b = false;
        File saveFile = new File(saveAddress);

        try {
            URL e = new URL(url);
            URLConnection conn = e.openConnection();
            FileUtils.forceMkdir(saveFile.getParentFile());
            InputStream inputStream = conn.getInputStream();
            bis = new BufferedInputStream(inputStream);
            fos = new FileOutputStream(saveFile);
            bos = new BufferedOutputStream(fos);
            byte[] buf = new byte[2097152];
            boolean len = true;

            int len1;
            while((len1 = bis.read(buf)) != -1) {
                bos.write(buf, 0, len1);
            }

            b = true;
        } catch (Exception var21) {
            b = false;
        } finally {
            try {
                if(null != bis) {
                    bis.close();
                }

                if(null != bos) {
                    bos.close();
                }

                if(fos != null){
                    fos.close();
                }

                if(!b && saveFile.exists()) {
                    saveFile.delete();
                }
            } catch (IOException var20) {
                ;
            }

        }

        return b;
    }

    /**
     * 文件生成
     * @param path 路径
     * @return 生成失败成功
     */
    public boolean isHasFile(String path) {
        String separator = File.separator;
        File file = new File(path);
        String[] tempList = file.list();
        File temp = null;
        boolean bl = false;

        for(int i = 0; i < tempList.length; ++i) {
            if(path.endsWith(separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + separator + tempList[i]);
            }

            if(temp.isFile()) {
                bl = true;
                break;
            }

            if(temp.isDirectory()) {
                bl = this.isHasFile(path + separator + tempList[i], bl);
            }
        }

        return bl;
    }

    /**
     * 文件生成
     * @param path 路径
     * @param bl 判断参数
     * @return 生成成功失败
     */
    private boolean isHasFile(String path, boolean bl) {
        String separator = File.separator;
        File file = new File(path);
        String[] tempList = file.list();
        File temp = null;

        for(int i = 0; i < tempList.length; ++i) {
            if(path.endsWith(separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + separator + tempList[i]);
            }

            if(temp.isFile()) {
                bl = true;
                break;
            }

            if(temp.isDirectory()) {
                this.isHasFile(path + separator + tempList[i], bl);
            }
        }

        return bl;
    }

    /**
     * 保存文件到桌面
     * @param imageInf 图片情报
     * @param path 路径
     * @return 保存成功失败
     */
    public boolean saveFileToDisk(String imageInf, String path) {
        FileOutputStream var7 = null;
        try {
            if(imageInf == null) {
                return false;
            } else {
                BASE64Decoder e = new BASE64Decoder();
                byte[] b = e.decodeBuffer(imageInf);

                for(int out = 0; out < b.length; ++out) {
                    if(b[out] < 0) {
                        b[out] = (byte)(b[out] + 256);
                    }
                }

                var7 = new FileOutputStream(path);
                var7.write(b);
                var7.flush();
                return true;
            }
        } catch (IOException var6) {
            return false;
        }finally {
            if(var7 != null){
                try{
                    var7.close();
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件压缩
     * @param args 参数
     */
    public static void main(String[] args) {
        if(compressPic("f:/myself.jpg", "f:/myself1.jpg")) {
            System.out.println("压缩成功！");
        } else {
            System.out.println("压缩失败！");
        }

    }

    /**
     * 图片压缩
     * @param srcFilePath 文件路径
     * @param descFilePath 文件路径
     * @return 压缩成功失败
     */
    public static boolean compressPic(String srcFilePath, String descFilePath) {
        File picture = new File(srcFilePath);
        long size = picture.length() / 1024L / 1024L;
        if(size <= 1L) {
            return true;
        } else {
            File file = null;
            BufferedImage src = null;
            FileOutputStream out = null;
            ImageWriter imgWrier = (ImageWriter)ImageIO.getImageWritersByFormatName("jpg").next();
            JPEGImageWriteParam imgWriteParams = new JPEGImageWriteParam((Locale)null);
            imgWriteParams.setCompressionMode(2);
            imgWriteParams.setCompressionQuality(0.1F);
            imgWriteParams.setProgressiveMode(0);
            ColorModel colorModel = ColorModel.getRGBdefault();
            imgWriteParams.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

            try {
                if(StringUtils.isBlank(srcFilePath)) {
                    return false;
                } else {
                    file = new File(srcFilePath);
                    src = ImageIO.read(file);
                    out = new FileOutputStream(descFilePath);
                    imgWrier.reset();
                    imgWrier.setOutput(ImageIO.createImageOutputStream(out));
                    imgWrier.write((IIOMetadata)null, new IIOImage(src, (List)null, (IIOMetadata)null), imgWriteParams);
                    out.flush();
//                    out.close();
                    return true;
                }
            } catch (Exception var12) {
                var12.printStackTrace();
                return false;
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        }
    }
}
