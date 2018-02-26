 package cn.rfatx.app.util.client;
 


import cn.rfatx.core.diva.mapper.JsonMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


 /**
  * 创建WEB SERVICE的方法
  */
 public class RestServiceUtil
 {
   private static final String CHARSET = "utf-8";
   private static int httpTimeout = 1000000;


     /**
      * 请求
      * @param serviceName 服务名
      * @param ja 键值对
      * @param type 类型
      * @return 请求数据
      */
   @Deprecated
   public static String getRequestString(String serviceName, Map ja, String type)
   {
     Map<String, Object> json = new HashMap();
     json.put("type", "rest");
     json.put("to", type);
     json.put("service", serviceName);
     json.put("data", ja);
     return JsonMapper.nonEmptyMapper().toJson(json);
   }

     /**
      * 应答
      * @param request 请求
      * @param url 路径
      * @param isPost 请求方式
      * @return 编码为（UTF—8）字符串
      */
   @Deprecated
   public static String getStringResponse(String request, String url, boolean isPost) { return getStringResponse(request, url, isPost, httpTimeout); }
   
   @Deprecated
   private static String getStringResponse(String request, String url, boolean isPost, int timeoutInMS) {
     ByteArrayOutputStream outputStream = getResponse(request, url, isPost, timeoutInMS);
     if (outputStream != null) {
       try {
         return outputStream.toString("UTF-8");
       } catch (UnsupportedEncodingException e) {
         return null;
       }
     }
     return null;
   }

     /**
      * 捕获内存缓冲区的数据，转成字节数组
      * @param request 请求
      * @param url 路径
      * @param isPost 请求方式
      * @param timeoutInMS 请求时间
      * @return 字节组
      */
   @Deprecated
   public static ByteArrayOutputStream getResponse(String request, String url, boolean isPost, int timeoutInMS) { HttpURLConnection conn = null;
     InputStream inputStream = null;
     DataOutputStream out = null;
     try
     {
       if (isPost) {
         URL uUrl = new URL(url);
         conn = (HttpURLConnection)uUrl.openConnection();
         conn.setRequestMethod("POST");
       } else {
         if ((request != null) && (!request.isEmpty())) {
           if (url.contains("?")) {
             url = url + "&" + request;
           } else {
             url = url + "?" + request;
           }
         }
         URL uUrl = new URL(url);
         conn = (HttpURLConnection)uUrl.openConnection();
         conn.setRequestMethod("GET");
       }
       conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
       conn.setReadTimeout(timeoutInMS);
       conn.setConnectTimeout(timeoutInMS);
       conn.setDoOutput(true);
       conn.setDoInput(true);
       if (isPost) {
         out = new DataOutputStream(conn.getOutputStream());
         if (request != null) {
           out.write(request.getBytes());
         }
         out.flush();
       }
       inputStream = conn.getInputStream();
       byte[] bytes = new byte['Ѐ'];
       int len = 0;
       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       while ((len = inputStream.read(bytes)) != -1) {
         outputStream.write(bytes, 0, len);
       }
       return outputStream;
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       if (out != null) {
         try {
           out.close();
         } catch (IOException e) {
           e.printStackTrace();
         }
       }
       if (inputStream != null) {
         try {
           inputStream.close();
         } catch (IOException e) {
           e.printStackTrace();
         }
       }
       if (conn != null) {
         conn.disconnect();
       }
     }
     return null;
   }
 }
