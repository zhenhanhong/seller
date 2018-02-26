package cn.rfatx.app.util.message;


import cn.rfatx.app.util.ReadPropfileUtil;
import cn.rfatx.app.util.Utils;
import cn.rfatx.app.util.xml.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 发送手机短信
 */
public class SendPhoneMsg {
    private static SendPhoneMsg sendPhoneMsg = null;
    private final Logger log = LoggerFactory.getLogger(SendPhoneMsg.class);

    private SendPhoneMsg() {
        ReadPropfileUtil.getInstall();
        this.f_sendMsgUrl = ReadPropfileUtil.prop.getProperty("sendMsgUrl");
        ReadPropfileUtil.getInstall();
        this.f_sendMsgAccount = ReadPropfileUtil.prop.getProperty("sendMsgAccount");
        ReadPropfileUtil.getInstall();
        this.f_sendMsgPwd = ReadPropfileUtil.prop.getProperty("sendMsgUrl");
        ReadPropfileUtil.getInstall();
        this.f_sendMsgID = ReadPropfileUtil.prop.getProperty("sendMsgID");
        ReadPropfileUtil.getInstall();
        this.f_sendMsgContent = ReadPropfileUtil.prop.getProperty("sendMsgContent");
    }

    public static SendPhoneMsg getInstall() {
        if (null == sendPhoneMsg) {
            synchronized (SendPhoneMsg.class) {
                if (sendPhoneMsg == null) {
                    sendPhoneMsg = new SendPhoneMsg();
                }
            }
        }
        return sendPhoneMsg;
    }

    private String f_sendMsgUrl;
    private String f_sendMsgAccount;
    private String f_sendMsgPwd;
    private String f_sendMsgID;
    private String f_sendMsgContent;

    /**
     * 根据手机号发送短信
     * @param phone 电话号
     * @param codeNum 编号
     * @param sendMsgUrl 发送信息的url
     * @param sendMsgAccount 发送信息的用户
     * @param sendMsgContent 发送用户的通信商
     * @param sendMsgID 发送信息的ID
     * @param sendMsgPwd 发送信息密码
     * @return 发送是否成功
     */
    public boolean sendByPhoneNum(String phone, String codeNum, String sendMsgUrl, String sendMsgAccount, String sendMsgContent, String sendMsgID, String sendMsgPwd) {
        boolean flag = false;
        if ((Utils.isEmptyString(phone)) || (Utils.isEmptyString(codeNum))) {
            return flag;
        }
        if (Utils.isEmptyString(sendMsgUrl)) {
            return flag;
        }
        this.f_sendMsgUrl = (Utils.isEmptyString(sendMsgUrl) ? this.f_sendMsgUrl : sendMsgUrl);
        this.f_sendMsgID = (Utils.isEmptyString(sendMsgID) ? this.f_sendMsgID : sendMsgID);
        this.f_sendMsgAccount = (Utils.isEmptyString(sendMsgAccount) ? this.f_sendMsgAccount : sendMsgAccount);
        this.f_sendMsgPwd = (Utils.isEmptyString(sendMsgPwd) ? this.f_sendMsgPwd : sendMsgPwd);
        this.f_sendMsgContent = (Utils.isEmptyString(sendMsgContent) ? this.f_sendMsgUrl : sendMsgContent);
        try {
            String temp = this.f_sendMsgContent.replace("${codeNum}", codeNum);
            StringBuffer sbf = new StringBuffer();
            sbf.append("sname=").append(this.f_sendMsgAccount).append("&spwd=").append(this.f_sendMsgPwd).append("&scorpid=").append("&sprdid=").append(this.f_sendMsgID).append("&sdst=").append(phone).append("&smsg=").append(URLEncoder.encode(temp, "utf-8"));


            String result = SMS(sbf.toString(), this.f_sendMsgUrl);
            if (Utils.isEmptyString(result)) {
                return flag;
            }
            if ("0".equals(XmlUtil.getCenterStr(result, "State"))) {
            }
            return true;
        } catch (Exception e) {
            flag = false;
            this.log.error("短信发送异常,错误信息{}", e);
        }
        return flag;
    }

    /**
     * 发送短信前的网络判断
     * @param postData 请求数据
     * @param postUrl 请求地址
     * @return 网络连接是否成功
     */
    private String SMS(String postData, String postUrl) {
        InputStream is = null;
        OutputStream os = null;
        try {
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", String.valueOf(postData.getBytes().length));
            os = conn.getOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8");
            out.write(postData);
            out.flush();
            out.close();


            if (conn.getResponseCode() != 200) {
                this.log.info("短信连接失败");
                return "";
            }

            String result = "";
            is = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result = result + line + "\n";
            }
            in.close();
            return result;
        } catch (Exception e) {
            this.log.error("短信发送异常,错误信息{}", e);
            e.printStackTrace();
        }finally {
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
        return "";
    }
}
