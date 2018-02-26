package cn.rfatx.app.util;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * path工具类
 */
public class PathUtil {
    /**
     * 获取path权限
     * @return 权限路径
     */
    public static String getRootPath() {
        String classPath = "";
        String rootPath = "";
        try {
            if(null == PathUtil.class.getClassLoader().getResource("/")){
                classPath = URLDecoder.decode(PathUtil.class.getResource("/").getPath(), "utf-8");
            }else{
                classPath = URLDecoder.decode(PathUtil.class.getClassLoader().getResource("/").getPath(), "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (!"".equals(classPath)) {
            if(classPath.indexOf("/WEB-INF/classes") >= 0){
                if ("\\".equals(File.separator)) {
                    rootPath = classPath.substring(1, classPath.indexOf("/WEB-INF/classes"));
                    rootPath = rootPath.replace("/", "\\");
                }
                if ("/".equals(File.separator)) {
                    rootPath = classPath.substring(0, classPath.indexOf("/WEB-INF/classes"));
                    rootPath = rootPath.replace("\\", "/");
                }
            }else{
                rootPath = classPath;
            }
        }
        return rootPath;
    }

    /**
     * 获取ip地址
     * @param request 请求
     * @return ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /**
     * 获取 web-inf目录
     * @return path
     */
    public static String getWEB_INF(){
        return getRootPath() + "/WEB-INF";

    }
    /**
     * 获取html文件夹路径
     * @return path
     */
    public static String getHtmlPath(){
        return getWEB_INF() + "/webInformation/html/";
    }
}

