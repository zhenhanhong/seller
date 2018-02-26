package cn.rfatx.app.util.card;

import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;

/**
 * Created by T5-SK on 2017/9/15.
 */
public class CommonMethod {
    /**
     * 将两个16进制数的字符串异或并返回一个字符串
     * @param var1
     * @param var2
     * @return
     */
    public static String xor(String var1,String var2){
        String returnData=new BigInteger(var1,16).xor(new BigInteger(var2,16)).toString(16);
        int lengeth=Integer.max(var1.length(),var2.length());
        return StringUtils.leftPad(returnData,lengeth,'0');
    }
}
