package cn.rfatx.atc.util;
import cn.rfatx.atc.util.client.KnRestClient;
import cn.rfatx.core.diva.mapper.JsonMapper;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
/**
 * 微信客户端请求处理
 * @author huangad@coracle.com
 */
public class ClientUtils{
    private static final Logger log=LoggerFactory.getLogger(ClientUtils.class);
    public static Map<String,String> execute(String url,String paramString,String logMessage){
        Map<String,String> map=Maps.newHashMap();
        try{
            String returnStr=KnRestClient.restRequest(paramString,url);
            // TODO
            //WxHttpClient.execute(url,paramString);
            log.info("========>>"+logMessage+"：地址["+url+"]参数["+paramString+"]响应结果["+returnStr+"]");
            if(StringUtils.isNotBlank(returnStr)){
                map= JsonMapper.nonEmptyMapper().fromJson(returnStr,HashMap.class);
            }
        }catch(Exception e){
            log.info("<<====请求异常===="+logMessage+"：地址["+url+"]参数["+paramString+"]",e);
        }
        return map;
    }

    public static Map<String,String> execute(String url,Map<String,String> paramMap,String logMessage){
        String paramString=JsonMapper.nonDefaultMapper().toJson(paramMap);
        return execute(url,paramString,logMessage);
    }
}
