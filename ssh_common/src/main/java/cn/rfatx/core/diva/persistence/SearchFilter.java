package cn.rfatx.core.diva.persistence;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

/**
 * 检索过滤器
 */
public class SearchFilter{
    public String fieldName;
    public Object value;
    public Operator operator;
    public SearchFilter(String fieldName,Operator operator,Object value){
        this.fieldName=fieldName;
        this.value=value;
        this.operator=operator;
    }

    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME
     *
     * @param searchParams 检索条件
     * @return 信息
     */
    public static Map<String,SearchFilter> parse(Map<String,Object> searchParams){
        Map<String,SearchFilter> filters=Maps.newHashMap();
        for(Entry<String,Object> entry : searchParams.entrySet()){
            // 过滤掉空值
            String key=entry.getKey();
            Object value=entry.getValue();
            if(StringUtils.isBlank((String)value)){
                continue;
            }
            // 拆分operator与filedAttribute
            String[] names=StringUtils.split(key,"_");
            if(names.length!=2){
                throw new IllegalArgumentException(key+" is not a valid search filter name");
            }
            String filedName=names[1];
            Operator operator=Operator.valueOf(names[0]);
            // 创建searchFilter
            SearchFilter filter=new SearchFilter(filedName,operator,value);
            filters.put(key,filter);
        }
        return filters;
    }
    public enum Operator{
        EQ,NOT,LIKE,GT,LT,GTE,LTE
    }
}