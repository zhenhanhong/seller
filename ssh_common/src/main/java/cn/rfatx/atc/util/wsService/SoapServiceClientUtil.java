package cn.rfatx.atc.util.wsService;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dhc-user1 on 2017/9/3.
 */
public class SoapServiceClientUtil {
    private final static Logger logger = LoggerFactory.getLogger(SoapServiceClientUtil.class);

    public static void setWsSecurity(Client client, String user){
        // 开启SOAP日志
        if(logger.isDebugEnabled()){
            client.getInInterceptors().add(new LoggingInInterceptor());
            client.getOutInterceptors().add(new LoggingOutInterceptor());
        }

        Map<String ,Object> outProp = new HashMap();
        outProp.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        //添加用户名
        outProp.put(WSHandlerConstants.USER, user);
        outProp.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        outProp.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallbackHandler.class.getName());
        client.getOutInterceptors().add(new WSS4JOutInterceptor(outProp));
    }
}
