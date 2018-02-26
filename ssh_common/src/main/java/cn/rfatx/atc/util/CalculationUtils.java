package cn.rfatx.atc.util;
import java.math.BigDecimal;
/**
 * 数据计算处理类
 * Created by huanganding on 15/5/27.
 */
public class CalculationUtils{

    public static double divide(String num1,String num2){
        try{
            BigDecimal a=new BigDecimal(num1);
            BigDecimal b=new BigDecimal(num2);
            return a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0.0;
    }

    public static int divideToInt(String num1,String num2){
        try{
            BigDecimal a=new BigDecimal(num1);
            BigDecimal b=new BigDecimal(num2);
            return a.divide(b,1,BigDecimal.ROUND_HALF_UP).intValue();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static int multiplyToInt(String num1,String num2){
        try{
            BigDecimal a=new BigDecimal(num1);
            BigDecimal b=new BigDecimal(num2);
            return a.multiply(b).intValue();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static double multiplyToDouble(String num1,String num2){
        try{
            BigDecimal a=new BigDecimal(num1);
            BigDecimal b=new BigDecimal(num2);
            return a.multiply(b).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0.0;
    }

    public static double addToDouble(String num1,String num2){
        try{
            BigDecimal a=new BigDecimal(num1);
            BigDecimal b=new BigDecimal(num2);
            return a.add(b).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0.0;
    }
    // first stage modify start
    public static BigDecimal addToBigDecimal(String num1,String num2){
        try{
            BigDecimal a=new BigDecimal(num1);
            BigDecimal b=new BigDecimal(num2);
            return a.add(b).setScale(2,BigDecimal.ROUND_HALF_UP);
        }catch(Exception e){
            e.printStackTrace();
        }
        return BigDecimal.valueOf(0.0);
    }
    // first stage modify end

    public static double subToDouble(String num1,String num2){
        try{
            BigDecimal a=new BigDecimal(num1);
            BigDecimal b=new BigDecimal(num2);
            return a.subtract(b).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0.0;
    }

    public static void main(String[] args){
        double a=1.0;
        double b=4.0;


        System.out.println(divide(a+"",b+""));

        double aa=0.8999;
        double bb=100;
        System.out.println(multiplyToInt(aa+"",bb+""));
    }
}
