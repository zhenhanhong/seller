package cn.rfatx.atc.util.gdev;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GDEVCore {
    private static Logger log = LoggerFactory.getLogger(GDEVCore.class);


    /**
     * 应答数据编辑
     *
     * @param dataMap
     * @param resultCode 结果code
     * @param msg        结果信息
     * @param msg        结果信息
     * @return
     */
    public static String editResponseData(Map<String, Object> dataMap, String resultCode, String msg, String key) {

        JSONObject paramObj = new JSONObject();
        try {
            paramObj.put(GDEVConstant.Ret, resultCode);
            paramObj.put(GDEVConstant.Msg, msg);
            paramObj.put(GDEVConstant.Data, dataMap);

            String signMsg = paramObj.getString(GDEVConstant.Ret)
                    + paramObj.getString(GDEVConstant.Msg)
                    + paramObj.getString(GDEVConstant.Data);

            paramObj.put(GDEVConstant.Sig,
                    HMacMD5.getHmacMd5Str(key, signMsg));
        } catch (Exception e) {
            log.error("返回值设定失败！", e);
        }
        return paramObj.toString();
    }


}
