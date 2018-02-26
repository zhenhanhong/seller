package cn.rfatx.atc.util;

import cn.rfatx.core.diva.mapper.JsonMapper;

/**
 * 字体工具类
 * @author huangad@coracle.com
 */
public class PrintUtils{
    public static String toString(Object object){
        if(object==null){
            return null;
        }
        return JsonMapper.nonDefaultMapper().toJson(object);
    }
}
