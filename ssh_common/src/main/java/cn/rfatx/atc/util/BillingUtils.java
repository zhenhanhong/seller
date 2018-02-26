package cn.rfatx.atc.util;

import org.joda.time.DateTime;

import java.math.BigDecimal;
/**
 * 计费方案工具类
 * @author huang an ding
 */
public class BillingUtils{
    /**
     * 根据开始时间和结束时间判断是否在有效范围内
     * @param start 开始时间
     * @param end 结束时间
     * @return 失败成功
     */
    public static boolean nowInCoverage(Long start,Long end){
        Long now=DateTime.now().getMillis();
        if(end!=null&&end>0){
            return now>=start&&now<end;
        }
        return now>=start;
    }

    /**
     * 格式化结果
     * @param decimal 需要格式化的浮点数字
     * @return 格式化完成后的结果 0.00
     */
    public static double formatDoubleDecimal(double decimal){
        BigDecimal bigDecimal=new BigDecimal(decimal);
        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 格式化结果
     * @param decimal 需要格式化的浮点数字
     * @return 格式化完成后的结果 0.00000
     */
    public static double formatDoubleDecimal5(double decimal){
        BigDecimal bigDecimal=new BigDecimal(decimal);
        return bigDecimal.setScale(5,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
