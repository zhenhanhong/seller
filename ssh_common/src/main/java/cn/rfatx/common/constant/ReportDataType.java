package cn.rfatx.common.constant;

/**
 * Created by rf-author on 2017/03/16.
 */
public enum ReportDataType {
    STACKTYPE("堆状态"), PILETYPE("桩整体状态"), CHARGINGTYPE("充电位状态"), CHARGINGCURRENTTYPE("充电位实时数据");

    private ReportDataType(String s_type) {
        this.s_type = s_type;
    }

    private final String s_type;

    public String getTypeName() {
        return this.s_type;
    }
}
