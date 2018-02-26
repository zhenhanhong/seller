package cn.rfatx.redis.pileinfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 桩实体类
 * Created by rf-author on 2017/03/02.
 */
public class BasePileInfo implements Serializable {
    private String frameType = "";

    private String recordType = "";

    private String pileCode = "";

    private String dataType = "";

    private Map<String,Object> details = new HashMap<String, Object>();

    private LinkedHashMap<String, Object> values = new LinkedHashMap<>();

    public String getFrameType() {
        return frameType;
    }

    public void setFrameType(String frameType) {
        this.frameType = frameType;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getPileCode() {
        return pileCode;
    }

    public void setPileCode(String pileCode) {
        this.pileCode = pileCode;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
    public String getDataType() {
        return dataType;
    }

    public void setDataType() {
        this.dataType = frameType + recordType;
    }

    public LinkedHashMap<String, Object> getValues() {
        return values;
    }

    public void setValues(LinkedHashMap<String, Object> values) {
        this.values = values;
    }
}
