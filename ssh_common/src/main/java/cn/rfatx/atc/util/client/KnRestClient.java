package cn.rfatx.atc.util.client;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行post请求
 *
 * @author huangad@coracle.com.
 */
public class KnRestClient {
    private static Logger log = LoggerFactory.getLogger(KnRestClient.class);
    private static Client client = Client.create();

    public static void setConnectTimeOut(int time) {
        client.setConnectTimeout(time * 1000);
    }

    public static void setReadTimeOut(int time) {
        client.setReadTimeout(time * 1000);
    }

    public static String restRequest(String jsonStr, String url) {
        // return "ok";
        // 默认只执行一次
        return restRequest(jsonStr, url, 1);
    }

    /**
     * 如果不成功返回请求数据
     *
     * @param jsonStr 请求类型
     * @param url 地址
     * @param sumTryCount 请求次数
     * @return 请求数据
     */
    public static String restRequest(String jsonStr, String url, int sumTryCount) {
        log.info("==>url：" + url);
        WebResource webResource = client.resource(url);
        log.info("==>Post的数据：");
        log.info(jsonStr);
        String returnData = "";
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON_TYPE);

        int httpStatus = 0;
        ClientResponse response;
        int currentTryCount = 0;
        while (httpStatus != 200 && sumTryCount > 0) {
            currentTryCount++;
            log.info("==>尝试第" + currentTryCount + "请求...");
            response = builder.post(ClientResponse.class, jsonStr);
            returnData = response.getEntity(String.class);
            httpStatus = response.getStatus();
            log.info("==>返回的 Status:" + httpStatus);
            log.info("==>返回的的数据：");
            log.info(returnData);
            sumTryCount--;
        }
        return returnData;
    }
}
