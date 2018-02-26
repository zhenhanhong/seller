package util;


import cn.rfatx.core.diva.mapper.JsonMapper;
import cn.rfatx.core.diva.utils.Encodes;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 基础参数设定
 */
public class PlatFormDataUtil {
    /**
     * 接口版本号
     */
    public static final String KEY_VERSION = "version";
    /**
     * 参数字符集
     */
    public static final String KEY_CHARSET = "charset";
    /**
     * 签名值
     */
    public static final String KEY_SIGNATUREINFO = "signatureInfo";
    /**
     * 签名证书序列号
     */
    public static final String KEY_CERTSERIALNO = "certSerialNo";
    /**
     * 请求服务时间
     */
    public static final String KEY_REQUESTTIME = "requestTime";

    /**
     * 响应时间
     */
    public static final String KEY_RESPONSETIME = "responseTime";

    /**
     * 交易名称
     */
    public static final String KEY_TRADENAME = "tradeName";

//    /**
//     * request基础参数设定
//     * @param version 版本
//     * @param charset 编码
//     * @param certserialno certserialno
//     * @return 设定结果
//     */
//    public static Map<String, String> getBaseRequest(String version,String charset, String certserialno) {
//        Map<String, String> devMap = new HashMap<String, String>();
//        // 接口版本号
//        devMap.put(KEY_VERSION, version);
//        // 参数字符集
//        devMap.put(KEY_CHARSET, charset);
//        // 签名证书序列号
//        devMap.put(KEY_CERTSERIALNO, certserialno);
//
//        // 请求服务时间
//        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//        devMap.put(KEY_REQUESTTIME, format.format(new Date()));
//        return devMap;
//    }

    /**
     * response基础参数设定
     * @param certserialno certserialno
     * @return 设定结果
     */
    public static Map<String, String> getBaseResponse(String certserialno) {
        Map<String, String> devMap = new HashMap<String, String>();
        // 签名证书序列号
        devMap.put(KEY_CERTSERIALNO, certserialno);
        // 响应时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        devMap.put(KEY_RESPONSETIME, format.format(new Date()));
        //处理结果
        devMap.put("result", "1");
        return devMap;
    }

    /**
     * 从response取得数据
     * @param JsonStr JsonStr
     * @return 数据
     */
    public static Map<String, String> getDataFromResponse(String JsonStr) {
        Map<String, String> map = new HashMap<String, String>();
        map = JsonMapper.nonEmptyMapper().fromJson(JsonStr, Map.class);
        return map;
    }

    /**
     * 对参数进行数字签名
     *
     * @param devMap              参数
     * @param deleteEmptyValueKey true:删除空value的key , false:无视空value的key
     * @param doEncode true:进行URLEncode,false:不进行URLEncode
     * @return 数字签名
     * @throws Exception 异常
     */
    public static String getSignParaAsc(Map<String, String> devMap, boolean deleteEmptyValueKey, boolean doEncode) throws Exception {
        List<Map.Entry<String, String>> info = new ArrayList<Map.Entry<String, String>>(
                devMap.entrySet());
        Collections.sort(info, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey().toString());
            }
        });
        StringBuilder builder = new StringBuilder();
        String tempValue = "";
        for (int i = 0; i < info.size(); i++) {
            if (deleteEmptyValueKey) {
                if ("".equals(info.get(i).getValue())) {
                    continue;
                }
            }
            if (KEY_SIGNATUREINFO.equals(info.get(i).getKey())){ // || KEY_TRADENAME.equals(info.get(i).getKey())) {
                continue;
            }
//            if ("tradeName".equals(info.get(i).getKey())) {
//                doEncode = true;
//            }
            // nginx统一配置td，跳过
            if("td".equals(info.get(i).getKey())){
                continue;
            }
            builder.append(info.get(i).getKey());
            builder.append("=");
            tempValue = info.get(i).getValue();
            if (null == tempValue){
                tempValue = "";
            }
            if (doEncode){
                    tempValue = URLEncoder.encode(tempValue,"UTF-8");
                builder.append(tempValue);
            }else{
                builder.append(tempValue);
            }
            devMap.put(info.get(i).getKey(),tempValue);
            if (i != info.size() - 1) {
                builder.append("&");
            }
        }
        return builder.toString();
    }

    /**
     * 对参数进行数字签名
     *
     * @param signByte 参数 例：charset=utf-8&merchantNo=23497234）
     * @param encoding UTF-8
     * @return 数字签名
     * @throws Exception 异常
     */
    public static byte[] getSignParaBytes(String signByte, String encoding) throws Exception {
        byte[] signBytes;
        if ("".equals(encoding) || null == encoding) {
            signBytes = signByte.getBytes("UTF-8");
        } else {
            signBytes = signByte.getBytes(encoding);
        }
        return signBytes;
    }

    /**
     * Form数据变换（例：charset=utf-8&merchantNo=23497234）
     *
     * @param devMap 参数
     * @return 数据变换
     * @throws Exception 异常
     */
    public static String getFormData(Map<String, String> devMap) throws Exception {
        List<Map.Entry<String, String>> info = new ArrayList<Map.Entry<String, String>>(
                devMap.entrySet());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < info.size(); i++) {
            builder.append(info.get(i).getKey());
            builder.append("=");
            String value = info.get(i).getValue();
            if (!"".equals(value)) {
                value = Encodes.urlEncode(value);
            }
            builder.append(value);
            if (i != info.size() - 1) {
                builder.append("&");
            }
        }
        return builder.toString();
    }

}