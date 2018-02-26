package cn.rfatx.atc.util.wsService;

import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dhc-user1 on 2017/9/3.
 */
public class SoapServiceServerUtil {
    private final static Logger logger = LoggerFactory.getLogger(SoapServiceServerUtil.class);

    public static void setWsSecurity(ServerFactoryBean jsfb){
        // 开启SOAP日志
        if(logger.isDebugEnabled()){
            jsfb.getInInterceptors().add(new LoggingInInterceptor());
            jsfb.getOutInterceptors().add(new LoggingOutInterceptor());
        }

        Map<String ,Object > inProp = new HashMap();
        inProp.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        inProp.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        inProp.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerPasswordCallbackHandler.class.getName());

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProp);
        jsfb.getInInterceptors().add(wssIn);
    }
}
