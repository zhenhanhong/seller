//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.rfatx.app.util.client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 工具类，为系统提供通用http访问操作方法
 */
public class HttpUtil {
    public HttpUtil() {
    }

    /**
     * 发送POST请求
     * @param url 地址路径
     * @param params 路径参数
     * @return 字符串
     */
    @Deprecated
    public static String http(String url, Map<String, String> params) {
        URL u = null;
        HttpURLConnection con = null;
        StringBuffer sb = new StringBuffer();
        OutputStreamWriter buffer1 = null;
        OutputStream os = null;
        if(params != null) {
            Iterator buffer = params.entrySet().iterator();

            while(buffer.hasNext()) {
                Entry e = (Entry)buffer.next();
                sb.append((String)e.getKey());
                sb.append("=");
                sb.append((String)e.getValue());
                sb.append("&");
            }

            sb.substring(0, sb.length() - 1);
        }

        try {
            u = new URL(url);
            con = (HttpURLConnection)u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            os = con.getOutputStream();
            buffer1 = new OutputStreamWriter(os, "UTF-8");
            buffer1.write(sb.toString());
            buffer1.flush();
            buffer1.close();
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
            try {
                if (buffer1 != null) {

                    buffer1.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        StringBuffer buffer2 = new StringBuffer();
        BufferedReader e1 = null;
        InputStream is = null;

        if (con != null) {
            try {
                is = con.getInputStream();
                e1 = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                String temp;
                while ((temp = e1.readLine()) != null) {
                    buffer2.append(temp);
                    buffer2.append("\n");
                }
            } catch (Exception var13) {
                var13.printStackTrace();
            }finally {
                try {
                    if (e1 != null) {
                        e1.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return buffer2.toString();
    }

    /**
     * 发送GET请求
     * @param httpUrl 网址
     * @param paramValue 参数值
     * @param methodType 方法
     * @return 请求是否成功
     * @throws Exception 请求异常
     */
    @Deprecated
    public static String sendHttpUrlRequest(String httpUrl, String paramValue, String methodType) throws Exception {
        HttpURLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;
        try {
            if ("get".equalsIgnoreCase(methodType)) {
                conn = (HttpURLConnection) (new URL(httpUrl)).openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
            } else {
                conn = (HttpURLConnection) (new URL(httpUrl)).openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(paramValue.length()));
                os = conn.getOutputStream();
                os.write(paramValue.getBytes());
            }
            is = conn.getInputStream();
            return conn.getResponseCode() == 200 ? readIO(is) : "";
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * 读取IO流
     * @param in 读取
     * @return 是否读取成功
     * @throws IOException IO流异常
     */
    @Deprecated
    public static String readIO(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean len = false;
        byte[] buffer = new byte[1024];

        int len1;
        while((len1 = in.read(buffer)) != -1) {
            out.write(buffer, 0, len1);
        }

        String jsonStr = out.toString();
        in.close();
        out.close();
        return jsonStr;
    }

    /**
     * 执行程序并返回结果
     * @param url 程序路径
     * @param param 参数
     * @return 结果是否为空
     */
    public static String execute(String url, String param) {
        OutputStreamWriter osw = null;
        BufferedReader in = null;
        String result = "";
        HttpURLConnection conn = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            URL ex = new URL(url);
            conn = (HttpURLConnection)ex.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("content-type", "text/html");
            conn.setRequestProperty("Charset", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            os = conn.getOutputStream();
            osw = new OutputStreamWriter(os, "utf-8");
            osw.write(param);
            osw.flush();

            String line;
            is = conn.getInputStream();
            for(in = new BufferedReader(new InputStreamReader(is, "utf-8")); (line = in.readLine()) != null; result = result + line) {
                ;
            }
        } catch (Exception var16) {
            result = null;
            var16.printStackTrace();
        } finally {
            try {
                if(osw != null) {
                    osw.close();
                }

                if(in != null) {
                    in.close();
                }

                if(null != conn) {
                    conn.disconnect();
                }

                if(is != null){
                    is.close();
                }

                if(os != null){
                    os.close();
                }
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

        return result;
    }
}
