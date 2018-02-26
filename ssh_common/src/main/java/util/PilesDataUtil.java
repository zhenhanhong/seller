package util;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by rf-author on 2017/3/30.
 */
public class PilesDataUtil {
    private final Logger logger = LoggerFactory.getLogger(PilesDataUtil.class);

    /**
     * BCD码转字符串。
     *
     * @param bcd
     * @return
     */
    public static String bcd2str(String bcd){
        byte[] bytes = bcd.getBytes();
        StringBuffer temp = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
            temp.append((byte) (bytes[i] & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
                .toString().substring(1) : temp.toString();
    }

    /**
     * 字符串转BCD码。
     *
     * @param asc
     * @return
     */
    public static String str2bcd(String asc){
        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }
        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        String rtnStr = "";
        rtnStr = new String(bbt);
        return rtnStr;
    }

    /**
     * 位数不足的字符串左补0.
     *
     * @param str 需补位字符串
     * @param length 总长度
     * @return
     */
    public static String paddingData(String str, int length){
        int strLen = str.length();
        String padStr = "";
        if(strLen < length){
            for (int i = 0; i < length - strLen; i++) {
                padStr = padStr + "0";
            }
            str = padStr + str;
        }
        return str;
    }

    /**
     * 位数不足的字符串右补0.
     *
     * @param str 需补位字符串
     * @param length 总长度
     * @return
     */
    public static String paddingRightData(String str, int length){
        int strLen = str.length();
        String padStr = "";
        if(strLen < length){
            for (int i = 0; i < length - strLen; i++) {
                padStr = padStr + "0";
            }
            str += padStr;
        }
        return str;
    }
    /**
     * Get Data from BCD String.get off all Zero on left side.
     * Then take left data to turn to OCX String.
     *
     * @param bcd
     * @return
     */
    public static String reverseBcdByLength(String bcd){
        String[] cd = bcd.split("");
        int startIdx = 0;
        for (int i = 0; i < cd.length; i++) {
            if(!cd[i].equals("0")){
                startIdx = i;
                break;
            }
        }

        String subStr = bcd.substring(startIdx);
        String ret = str2bcd(subStr);
        return ret;
    }

    public static void main(String[] args){
//        String[] test = "0004477".split("");
//        System.out.println(reverseBcdByLength("00000000333431313839"));
//        String src = "ED7372FB81C150DD8FCDAAB350463034EE996395";
//        System.out.println("str : " + bcd2str(src));
//        System.out.println("bcd : " + str2bcd(src));
//        System.out.println("urlEncode: " + URLEncoder.encode(str2bcd(src)));
//        System.out.println("padding : " + paddingData(str2bcd(src), 32));
//        System.out.println("return : " + bcd2str(str2bcd(src)));

        try {
            JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory
                    .newInstance();

            //服务地址，请根据需要修改
            Client client = factory
                    .createClient("http://61.144.29.107:9095/axis/services/CDPTService_wcwc?wsdl");
//            http://61.144.29.107:9095/axis/services/CDPTService_pdyn?wsdl
//            账号：pdyn  密码：AtcPdyn_@017

            //Client client = factory
            //.createClient("http://88.128.132.36:9000/axis/services/CDPTService_yht?wsdl");

            //增加拦截器，getOutInterceptors是调用服务前拦截
            //getInInterceptors是被调用服务前拦截
//            client.getOutInterceptors().add(new AddSoapHeader());

            Object[] obj = client
                    .invoke("pushData",
                            "CHARGER_DATA",
                            "2016-7-5 00:00",
                            "2016-7-6 00:00",
                            "<xml><CHARGER_DATA><CHARGERCODE>160602006007</CHARGERCODE><DATADATE>2016-07-06 19:53:33</DATADATE><DLA>0.00</DLA><DVA>229.3</DVA><JCD>0</JCD></CHARGER_DATA></xml>");
            System.out.println("resp:" + obj[0]);
        } catch (Exception err) {
            //System.out.println(err.toString());
            err.printStackTrace();
        }
    }
}
