package cn.rfatx.atc.service.util.sms;

import cn.rfatx.atc.util.DDSetting;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 短信格式的处理
 * Created by think on 2015/3/30.
 */
public class SmsUtil {
    private static Logger log= LoggerFactory.getLogger(SmsUtil.class);
   static Resource resource=new ClassPathResource("/sms.properties");

    private static String readValue(String key) {
        Properties props=null;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        }catch (Exception e){
            e.printStackTrace();
        }
        String value = props.getProperty(key);
        return value;
    }

    /**
     * 设置短信发送的格式
     * @param message 信息
     * @param phones 电话号
     * @return 发送成功失败
     */
    public  static boolean sendMessage(String message,String phones){
        boolean flag=false;
        PostMethod getMethod=null;
        log.info("短信发送开始====>phone:"+phones+",内容:"+message+",时间:"+ DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        try {
            String url =readValue(DDSetting.URL);
            HttpClient httpClient = new HttpClient();
            // 公网访问
            ResourceBundle resourceBundle = ResourceBundle.getBundle("ftpConfig");
            String isProxy = resourceBundle.getString("isProxy");
            if ("1".equals(isProxy)) {
                log.info("通过网络代理访问");
                httpClient.getHostConfiguration().setProxy("172.18.174.116", 31281);
                httpClient.getParams().setAuthenticationPreemptive(true);
                httpClient.getState().setProxyCredentials(AuthScope.ANY, new UsernamePasswordCredentials("root", "Atxrf@2017#web"));
            }
            // 公网访问
            httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
            getMethod = new PostMethod(url);
            String Content= java.net.URLEncoder.encode(message, "UTF-8");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            NameValuePair[] data = {
                    new NameValuePair("UserID", readValue(DDSetting.USER_ID)),
                    new NameValuePair("Account", readValue(DDSetting.ACCOUNT)),
                    new NameValuePair("Password", readValue(DDSetting.PASSWORD)),
                    new NameValuePair("Phones", phones),
                    new NameValuePair("SendType", "1"),
                    new NameValuePair("SendTime",""),
                    new NameValuePair("PostFixNumber", ""),
                    new NameValuePair("Content", Content) };
            getMethod.setRequestBody(data);
            getMethod.addRequestHeader("Connection", "close");

            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                flag=false;
                System.err.println("Method failed: "+ getMethod.getStatusLine());
            }
            byte[] responseBody = getMethod.getResponseBody();
            String str = new String(responseBody, "utf-8");
            if (str.contains("GBK")) {
                str = str.replaceAll("GBK", "utf-8");
            }
            int beginPoint = str.indexOf("<RetCode>");
            int endPoint = str.indexOf("</RetCode>");
            String result = "RetCode=";
            System.out.println(result + str.substring(beginPoint + 9, endPoint));
            System.out.println(str);
            flag=true;
            // return getMethod.getResponseBodyAsString();
        } catch (HttpException e) {
            flag=false;
        } catch (IOException e) {
            flag=false;
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
            }
        }
        log.info("====>短信发送结束!result:"+flag);
        return flag;
    }

}
