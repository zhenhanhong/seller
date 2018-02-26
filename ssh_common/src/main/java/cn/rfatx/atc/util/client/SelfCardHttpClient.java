package cn.rfatx.atc.util.client;

import cn.rfatx.app.util.PathUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * 自发卡通信用工具类
 *
 * Created by tony on 2017/8/7.
 */
public class SelfCardHttpClient {

    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String BASE_LOCAL_PATH = "/classes/ftl/selfCard";
    private static Logger log = LoggerFactory.getLogger(SlsyHttpClient.class);

    public static void sendFrontMsg(String templateName, Map<String, String> params, HttpServletResponse response) throws Exception {
        String htmlText = parse(templateName, params);
        log.debug("自发卡送信内容 start");
        log.debug(htmlText);
        log.debug("自发卡送信内容 end");
        sendHtml(htmlText, response);
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
        cfg.setDirectoryForTemplateLoading(new File(getBaseLocalPath()));
        Template template = cfg.getTemplate(templateName, CHARSET_UTF8);
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
            Template template = cfg.getTemplate("template", CHARSET_UTF8);
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
     * 以HTML FORM形式向自发卡发起请求，并跳转到自发卡画面(自发卡异步接口)
     *
     * @param htmlText 向自发卡送信内容字符串
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
        return PathUtil.getWEB_INF() + BASE_LOCAL_PATH;
    }


}
