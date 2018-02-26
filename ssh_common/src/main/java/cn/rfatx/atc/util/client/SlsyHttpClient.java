package cn.rfatx.atc.util.client;

import cn.rfatx.app.util.PathUtil;
import cn.rfatx.core.diva.mapper.JsonMapper;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import util.CertificateUtils;
import util.FtpClientUtil;
import util.PlatFormDataUtil;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 商联通信用工具类
 */

public class SlsyHttpClient {

    private static String CHARSET_UTF8 = "UTF-8";
    private static Logger log = LoggerFactory.getLogger(SlsyHttpClient.class);

    private static String FTP_URL = "192.168.67.136";
    private static int FTP_PORT = 21;
    private static String FTP_USER = "ftpuser";
    private static String FTP_PASSWORD = "ftp123";
    private static String FTP_DIR = "/home/ftpuser/slsy/";

    private static String BASE_LOCAL_PATH = "/slsy/";


    public static void sendFrontMsg(String templateName, Map<String, String> params, String logMessage, HttpServletResponse response) throws Exception {

        Map<String, String> slsyInfo = getSlsyInfo();
        // 接口版本号
        params.put("version", slsyInfo.get("slsyVersion"));
        // 参数字符集
        params.put("charset", slsyInfo.get("slsyCharset"));
        // 商户身份ID
        params.put("merchantNo", slsyInfo.get("slsyMerchantNo"));
        String strBeforeSign = PlatFormDataUtil.getSignParaAsc(params, false,false);

        String localPath = getBaseLocalPath();
        FtpClientUtil ftpClientUtil = new FtpClientUtil(FTP_URL, FTP_PORT, FTP_USER, FTP_PASSWORD);
        ftpClientUtil.ftpLogin();

        File storeFile = new File(localPath + "slsy.store");
        if(!storeFile.exists()){
            ftpClientUtil.downloadFile("slsy.store", localPath, FTP_DIR);
        }
        storeFile = new File(localPath + templateName);
        if(!storeFile.exists()){
            ftpClientUtil.downloadFile(templateName, localPath, FTP_DIR);
        }
        ftpClientUtil.ftpLogOut();

        // 获取签名后字符串
        String signStr = CertificateUtils.signToBase64(strBeforeSign.getBytes(CHARSET_UTF8), localPath + "slsy.store", slsyInfo.get("slsyStoreAlias"), slsyInfo.get("slsyStorePass"));
        params.put("signatureInfo", signStr);
        String htmlText = parse(templateName, params);
        File localFile = new File(localPath + "slsy.store");
//        localFile.delete();
        localFile = new File(localPath + templateName);
//        localFile.delete();
        log.info("商联送信内容 start");
        log.info(htmlText);
        log.info("商联送信内容 end");
        sendHtml(htmlText, response);
    }

    public static String execute(Map<String, String> paramMap, String logMessage) throws Exception {
        Map<String, String> slsyInfo = getSlsyInfo();
        // 接口版本号
        paramMap.put("version", slsyInfo.get("slsyVersion"));
        // 参数字符集
        paramMap.put("charset", slsyInfo.get("slsyCharset"));
        // 商户身份ID
        paramMap.put("merchantNo", slsyInfo.get("slsyMerchantNo"));

        String localPath = getBaseLocalPath();
        FtpClientUtil ftpClientUtil = new FtpClientUtil(FTP_URL, FTP_PORT, FTP_USER, FTP_PASSWORD);
        ftpClientUtil.ftpLogin();

        File storeFile = new File(localPath + "slsy.store");
        if(!storeFile.exists()){
            ftpClientUtil.downloadFile("slsy.store", localPath, FTP_DIR);
        }
        ftpClientUtil.ftpLogOut();

        String strBeforeSign = "";
        strBeforeSign = PlatFormDataUtil.getSignParaAsc(paramMap, false,true);
        log.error(logMessage + "开始");
        log.error(strBeforeSign);
        String signByte = CertificateUtils.signToBase64(strBeforeSign.getBytes("UTF-8"), localPath + "slsy.store", slsyInfo.get("slsyStoreAlias"), slsyInfo.get("slsyStorePass"));
        paramMap.put("signatureInfo", signByte);

        ObjectMapper om = new ObjectMapper();
        log.error("商联调用参数【" + logMessage + "】：" + om.writeValueAsString(paramMap));
        String retMsg = urlPostForString(slsyInfo.get("slsyBackUrl"), paramMap);
        log.error("商联调用返回值【" + logMessage + "】：" + retMsg);
        File localFile = new File(localPath + "slsy.store");
//        localFile.delete();
        return retMsg;
    }

    /**
     * 发送Http Post请求(默认重试3次)
     *
     * @param params 请求参数
     * @return 请求次数
     */
    public static String urlPostForString(String url, Map<String, String> params) {

        int retryCount = 3;
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(retryCount, false));
        if (null != params && 0 != params.size()) {
            NameValuePair[] data = new NameValuePair[params.size()];
            Object[] keys = params.keySet().toArray();
            for (int i = 0; i < keys.length; i++) {
                String value = (String) params.get(keys[i]);
                data[i] = new NameValuePair((String) keys[i], value);
            }
            postMethod.setRequestBody(data);
        }

        try {
            HttpClient client = new HttpClient();
            ResourceBundle resourceBundle = ResourceBundle.getBundle("ftpConfig");
            String isProxy = resourceBundle.getString("isProxy");
            if ("1".equals(isProxy)) {
                log.info("通过网络代理访问");
                client.getHostConfiguration().setProxy("172.18.174.116", 31281);
                client.getParams().setAuthenticationPreemptive(true);
                client.getState().setProxyCredentials(AuthScope.ANY, new UsernamePasswordCredentials("root", "Atxrf@2017#web"));
            }
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.error("请求状态不为成功statusCode[{}]", postMethod.getStatusLine());
            } else {
                String result = postMethod.getResponseBodyAsString();
                if (log.isDebugEnabled())
                    log.debug("请求成功result[{}]", result);
                return result;
            }
        } catch (HttpException e) {
            log.error("请检查请求协议", e);
            throw new RuntimeException("请检查请求协议", e);
        } catch (IOException e) {
            log.error("网络异常,已经重试了[{}]次", retryCount, e);
            throw new RuntimeException("网络异常,已经重试了[{}]次", e);
        } finally {
            postMethod.releaseConnection();
        }
        return null;
    }

    /**
     * 发送Https Post请求(默认重试3次)
     *
     * @param url    请求URL
     * @param params 请求参数
     * @return  请求数据
     */
    public static String urlPostHttps(String url, Map<String, String> params) {
        if (!url.startsWith("https")) {
            return urlPostForString(url, params);
        } else {
            HttpsURLConnection conn = null;
            try {
                String postParams = "";
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    postParams += entry.getKey() + "=" + entry.getValue() + "&";
                }
                if (postParams.length() > 0) {
                    postParams = postParams.substring(0, postParams.lastIndexOf("&"));
                }
                postParams = postParams.replaceAll("\\+", "%2B");
                SSLContext context = SSLContext.getInstance("SSL");
                context.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new SecureRandom());
                URL curl = new URL(url);
                conn = (HttpsURLConnection) curl.openConnection();
                conn.setSSLSocketFactory(context.getSocketFactory());
                conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
                conn.setRequestProperty("Cache-Control", "no-cache");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.getOutputStream().write(postParams.getBytes());
                conn.getOutputStream().flush();
                conn.getOutputStream().close();
                conn.connect();
                int statusCode = conn.getResponseCode();
                if (HttpStatus.SC_OK == statusCode) {
                    InputStream is = conn.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    BufferedReader br = new BufferedReader(new InputStreamReader(bis));
                    StringBuffer buff = new StringBuffer();
                    String line = null;
                    while (null != (line = br.readLine())) {
                        buff.append(line);
                    }
                    br.close();
                    return buff.toString();
                } else {
                    log.error("请求状态不为成功statusCode[{}]", statusCode);
                }
            } catch (IOException e) {
                log.error("io异常", e);
            } catch (KeyManagementException e) {
                log.error("KeyManagementException异常", e);
            } catch (NoSuchAlgorithmException e) {
                log.error("算法不存在异常", e);
            } finally {
                if (null != conn) {
                    conn.disconnect();
                }
            }
            return null;
        }
    }

    private static Map<String, String> getSlsyInfo() throws Exception {

        String localPath = getBaseLocalPath();
        Map<String, String> slsyInfo = new HashMap<String, String>();
        FtpClientUtil ftpClientUtil = new FtpClientUtil(FTP_URL, FTP_PORT, FTP_USER, FTP_PASSWORD);
        ftpClientUtil.ftpLogin();
        File storeFile = new File(localPath + "slsy.info");
        if(!storeFile.exists()){
            ftpClientUtil.downloadFile("slsy.info", localPath, FTP_DIR);
        }
        ftpClientUtil.ftpLogOut();

        File localFile = new File(localPath + "slsy.info");
        FileInputStream fileInputSteam = null;
        InputStreamReader inStrmReader = null;
        BufferedReader inputData = null;
        try {
            fileInputSteam = new FileInputStream(localFile);
            inStrmReader = new InputStreamReader(fileInputSteam, "UTF-8");
            inputData = new BufferedReader(inStrmReader);
            String temBuffer;
            while ((temBuffer = inputData.readLine()) != null) {
                String[] tempLine = temBuffer.split("=");
                slsyInfo.put(tempLine[0], tempLine[1]);
            }
        } finally {
            if (null != inputData) {
                inputData.close();
            }
            if (null != inStrmReader) {
                inStrmReader.close();
            }
            if (null != fileInputSteam) {
                fileInputSteam.close();
            }
        }
//        localFile.delete();
        return slsyInfo;
    }

    /**
     * 商联返回信息验证签名
     *
     * @param retStr 商联返回信息
     * @return 验签结果
     * @throws Exception 异常
     */
    public static boolean verifySignSlsy(String retStr) throws Exception {
        String localPath = getBaseLocalPath();
        FtpClientUtil ftpClientUtil = new FtpClientUtil(FTP_URL, FTP_PORT, FTP_USER, FTP_PASSWORD);
        ftpClientUtil.ftpLogin();
        File storeFile = new File(localPath + "slsy.cer");
        if(!storeFile.exists()){
            ftpClientUtil.downloadFile("slsy.cer", localPath, FTP_DIR);
        }
        ftpClientUtil.ftpLogOut();
        Map<String, String> map = JsonMapper.nonEmptyMapper().fromJson(retStr, HashMap.class);
        String signatureInfo = map.get("signatureInfo");
        map.remove("signatureInfo");
        String paraString = PlatFormDataUtil.getSignParaAsc(map, false,false);
        boolean result = CertificateUtils.verifySign(paraString.getBytes("UTF-8"), signatureInfo, localPath + "slsy.cer");
        File localFile = new File(localPath + "slsy.cer");
//        localFile.delete();
        return result;
    }

    /**
     * 将模板中的变量用Map中的内容替换，返回替换后的字符串形式内容
     *
     * @param templateName 模板文件名
     * @param obj          替换模板变量Map
     * @return 替换后字符串
     * @throws Exception 异常
     */
    private static String parse(String templateName, Object obj)
            throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
        // 获取模板
//        cfg.setClassForTemplateLoading(SlsyHttpClient.class, "/slsy/");
        cfg.setDirectoryForTemplateLoading(new File(getBaseLocalPath()));
        Template template = cfg.getTemplate(templateName, "UTF-8");
        // 模板的xml
        String templateStr = template.toString();
        return writeXML(obj, templateStr);
    }

    private static String writeXML(Object o, String templateContent) throws Exception {
        // 创建Configuration对象
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
        // 创建StringTemplateLoader对象
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        // 传入的xml字符串
        stringLoader.putTemplate("template", templateContent);
        // 设定模板
        cfg.setTemplateLoader(stringLoader);
        // 创建StringWriter对象
        StringWriter out = new StringWriter();
        // 获取字符串模板
        try {
            Template template = cfg.getTemplate("template", "UTF-8");
            // 对象值写入xml模板
            template.process(o, out);
        } catch (IOException e) {
            throw e;
        } catch (TemplateException e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return out.toString();
    }

    /**
     * 以HTML FORM形式向商联发起请求，并跳转到商联画面(商联异步接口)
     *
     * @param htmlText 向商联送信内容字符串
     * @param response 应答
     * @throws Exception 异常
     */
    public static void sendHtml(String htmlText, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding(CHARSET_UTF8);
        response.setContentType("text/html");
        response.getWriter().write(htmlText);
        response.getWriter().flush();
    }

    private static String getBaseLocalPath() {
        return PathUtil.getRootPath() + BASE_LOCAL_PATH;
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

}
