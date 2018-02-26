package cn.rfatx.atc.util;
import java.io.File;

import cn.rfatx.api.Setting;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
/**
 * 图片路径处理
 * Created by chenying on 2015/3/30.
 */
public class FileUtils{
    private static final Logger logger=LoggerFactory.getLogger(FileUtils.class);


    /**
     * 获取上传图片文件存储的路径
     *
     * @param file 图片文件
     *
     * @return 图片文件存储的路径
     * @throws Exception
     */
    public static String GetImageAddressByFile(MultipartFile file) throws Exception {
        StringBuffer imageAddress = new StringBuffer();
        if (null != file && file.getSize() != 0) {//上传文件不为空
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();//扩展名
            if (!Strings.isNullOrEmpty(fileExt)) {
                Long currentTime = System.currentTimeMillis();
                String filePath = Setting.BASEADDRESS + "/" + currentTime, backPath = filePath + "." + fileExt; //安装包存放路径前缀  没有后缀名
                File localFile = new File(webApplicationContext.getServletContext().getRealPath(backPath));
                if (!localFile.getParentFile().exists()) {
                    localFile.getParentFile().mkdirs();
                }
                file.transferTo(localFile);
                imageAddress.append(backPath);
            }
        }

        return imageAddress.toString();
    }
}
