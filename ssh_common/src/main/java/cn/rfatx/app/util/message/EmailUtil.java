package cn.rfatx.app.util.message;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * email工具类
 */
public class EmailUtil {
    private static String emailHostName = "";
    private static String emailAddress = "";
    private static String emailPwd = "";

    private static String emailAuthor = "";
    private static String emailTitle = "";
    private static final Logger log = LoggerFactory.getLogger(EmailUtil.class);

    /**
     * 发送email
     * @param emailAddr 邮件地址
     * @param emailName 邮件名
     * @param msg 邮件信息
     * @return  发送失败成功
     */
    public static boolean sendEmail(String emailAddr, String emailName, String msg) {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(emailHostName);
        email.setAuthentication(emailAddress, emailPwd);
        email.setCharset("UTF-8");
        StringBuffer sb = new StringBuffer();
        try {
            email.addTo(emailAddr, emailName);
            email.setFrom(emailAddress, emailAuthor);
            email.setSubject(emailTitle);
            email.setHtmlMsg(msg);
            email.send();
            sb.append("邮件发送成功,发送的内容为{发件人邮箱:").append(emailAddress).append(",收件人邮箱:").append(emailAddr).append(",发送的内容:").append(msg);
            log.info(sb.toString());
            return true;
        } catch (EmailException e) {
            sb.append("邮件发送失败,发送的内容为{发件人邮箱:").append(emailAddress).append(",收件人邮箱:").append(emailAddr).append(",发送的内容:").append(msg);
            log.error(sb.toString() + ",异常信息为{}", e);
        }
        return false;
    }
}

