 package cn.rfatx.app.util.date;
 


import com.google.common.collect.Lists;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


 /**
  * 时间工具类
  */
 public class DateUtil
 {
   private static volatile DateUtil dateUtil;
   
   public static DateUtil getInstance()
   {
       synchronized (DateUtil.class) {
         if (dateUtil == null) {
           dateUtil = new DateUtil();
         }
         return dateUtil;
       }
   }


     /**
      * 根据指定格式将指定日期格式化成字符串
      * @param date 时间
      * @param formatStr 字符串格式
      * @return 输出时间
      */
   public String formatDate(Date date, String formatStr)
   {
     SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
     String outDate = formatter.format(date);
     return outDate;
   }


     /**
      * 根据指定格式将指定字符串解析成日期
      * @param s 字符串
      * @param formateStr 字符串格式
      * @return 时间
      */
   public Date parseDate(String s, String formateStr)
   {
     SimpleDateFormat formatter = new SimpleDateFormat(formateStr);
     try
     {
       return formatter.parse(s);
     }
     catch (ParseException e) {
       e.printStackTrace();
     }
     return null;
   }


     /**
      * 默认日期格式日期比较
      * @param dateTime1 日期
      * @param dateTime2 日期
      * @return 日期
      */
   public int compareDate(Date dateTime1, Date dateTime2)
   {
     return dateTime1.compareTo(dateTime2);
   }


     /**
      * 根据指定的日期格式比较
      * @param dateTime1 日期
      * @param dateTime2 日期
      * @param formatStr 字符串格式
      * @return 完整时间
      */
   public int compareDate(Date dateTime1, Date dateTime2, String formatStr)
   {
     String t1 = formatDate(dateTime1, formatStr);
     String t2 = formatDate(dateTime2, formatStr);
     return compareDate(parseDate(t1, formatStr), parseDate(t2, formatStr));
   }


     /**
      * 最大最小时间的获得
      * @param date 日期
      * @param dateList 日期集合
      * @param formatStr 字符串格式
      * @param flag 判断参数
      * @return 日期
      */
   public List<Date> maxOrMinDate(Date date, List<Date> dateList, String formatStr, boolean flag)
   {
     List<Date> resultDate = Lists.newArrayList();
     String t1 = formatDate(date, formatStr);
     if (flag) {
       for (Date d : dateList) {
         String t2 = formatDate(d, formatStr);
         if (compareDate(parseDate(t1, formatStr), parseDate(t2, formatStr)) == -1) {
           resultDate.add(d);
         }
       }
     } else {
       for (Date d : dateList) {
         String t2 = formatDate(d, formatStr);
         if (compareDate(parseDate(t1, formatStr), parseDate(t2, formatStr)) == 1) {
           resultDate.add(d);
         }
       }
     }
     return resultDate;
   }


public static String getCurrentTime(){
    return new DateTime(System.currentTimeMillis()).toString("yyyyMMddHHmmss");
}

 }
