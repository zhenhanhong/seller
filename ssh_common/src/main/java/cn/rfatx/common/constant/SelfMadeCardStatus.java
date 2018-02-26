package cn.rfatx.common.constant;

/**
 * Created by rf-author on 2017/02/22.
 */
public enum SelfMadeCardStatus {
    UNSALE("1", "未售出"), UNACTIVE("2", "未激活"),ACTIVE("0", "已激活"),LOSS("3", "挂失"),BACK("4", "退卡"),FORBIDDEN("5", "禁用");

    private SelfMadeCardStatus(String value, String display) {
        this.value = value;
        this.display = display;
    }

    private final String value;
    private final String display;

    public String getValue() {
        return this.value;
    }
    public String getDisplay() {
        return this.display;
    }
}
